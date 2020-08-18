package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Profile;

public class ProfileValidator {
    public static List<String> validate(Profile p) {
        List<String> errors = new ArrayList<String>();

        /* String thumbnail_error = _validateThumbnail(p.getThumbnail());
        if(!thumbnail_error.equals("")) {
            errors.add(thumbnail_error);
        } */

        String content_error = _validateContent(p.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        String mainprogram_error = _validateMainprogram(p.getMainprogram());
        if(!mainprogram_error.equals("")) {
            errors.add(mainprogram_error);
        }

        String language_error = _validateLanguage(p.getLanguage());
        if(!language_error.equals("")) {
            errors.add(language_error);
        }

        String qualifications_error = _validateQualifications(p.getQualifications());
        if(!qualifications_error.equals("")) {
            errors.add(qualifications_error);
        }

        String snsblog_error = _validateSnsblog(p.getSnsblog());
        if(!snsblog_error.equals("")) {
            errors.add(snsblog_error);
        }

        return errors;
    }


    /* private static String _validateThumbnail(String thumbnail) {
        if(thumbnail == null || thumbnail.equals("")) {
            return "主なプログラムを入力してください。";
            }

        return "";
    } */

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "プロフィール紹介を入力してください。";
            }

        return "";
    }

    private static String _validateMainprogram(String mainprogram) {
        if(mainprogram == null || mainprogram.equals("")) {
            return "主なプログラムを入力してください。";
            }

        return "";
    }

    private static String _validateLanguage(String language) {
        if(language == null || language.equals("")) {
            return "対応言語を入力してください。";
            }

        return "";
    }

    private static String _validateQualifications(String qualifications) {
        if(qualifications == null || qualifications.equals("")) {
            return "保有資格を入力してください。";
            }

        return "";
    }

    private static String _validateSnsblog(String snsblog) {
        if(snsblog == null || snsblog.equals("")) {
            return "SNSアカウント・ブログ等を入力してください。";
            }

        return "";
    }
}