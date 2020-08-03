package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Instructor;
import utils.DBUtil;

public class InstructorValidator {
    public static List<String> validate(Instructor i, Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String code_error = _validateCode(i.getCode(), code_duplicate_check_flag);
        if(!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_error = _validateName(i.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String tname_error = _validateTname(i.getTname());
        if(!tname_error.equals("")) {
            errors.add(tname_error);
        }

        String password_error = _validatePassword(i.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        String mailAdress_error = _validateMailAdress(i.getMailAdress());
        if(!mailAdress_error.equals("")) {
            errors.add(mailAdress_error);
        }

        String officialHP_error = _validateOfficialHP(i.getOfficialHP());
        if(!officialHP_error.equals("")) {
            errors.add(officialHP_error);
        }

        return errors;
    }

    // ユーザー名
    private static String _validateCode(String code, Boolean code_duplicate_check_flag) {
        // 必須入力チェック
        if(code == null || code.equals("")) {
            return "ユーザー名を入力してください。";
        }

        // すでに登録されていユーザー名との重複チェック
        if(code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long instructors_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("code", code)
                                             .getSingleResult();
            em.close();
            if(instructors_count > 0) {
                return "入力されユーザー名の情報はすでに存在しています。";
            }
        }

        return "";
    }

    // 氏名の必須入力チェック
    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

 // ティーチャー名の必須入力チェック
    private static String _validateTname(String tname) {
        if(tname == null || tname.equals("")) {
            return "ティーチャー名を入力してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }

    // メールアドレスの必須入力チェック
    private static String _validateMailAdress(String mailAdress) {
        if(mailAdress == null || mailAdress.equals("")) {
            return "メールアドレスを入力してください。";
        }

        return "";
    }

    // 公式HPの必須入力チェック
    private static String _validateOfficialHP(String officialHP) {
        if(officialHP == null || officialHP.equals("")) {
            return "公式HPまたはSNSアカウントを入力してください。";
        }

        return "";
    }
}