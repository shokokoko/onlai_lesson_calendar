package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Lesson;

public class LessonValidator {
    public static List<String> validate(Lesson l) {
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(l.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String content_error = _validateContent(l.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        String target_error = _validateTarget(l.getTarget());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    private static String _validateTitle(String title) {
        if(title == null || title.equals("")) {
            return "レッスンタイトルを入力してください。";
            }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "レッスン内容を入力してください。";
            }

        return "";
    }

    private static String _validateTarget(String target) {
        if(target == null || target.equals("")) {
            return "対象者を入力してください。";
            }

        return "";
    }
}