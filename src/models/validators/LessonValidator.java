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

        String required_time_error = _validateRequired_time(l.getRequired_time());
        if(!required_time_error.equals("")) {
            errors.add(required_time_error);
        }

        String detail_error = _validateDetail(l.getDetail());
        if(!detail_error.equals("")) {
            errors.add(detail_error);
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

    private static String _validateRequired_time(Integer required_time) {
        if(required_time == null || required_time.equals("")) {
            return "所要時間を入力してください。";
            }

        return "";
    }

    private static String _validateDetail(String detail) {
        if(detail == null || detail.equals("")) {
            return "予約先・詳細を入力してください。";
            }

        return "";
    }
}