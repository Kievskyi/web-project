package com.example.webproject.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerOptions {
    Map<Step, Map<Choose, String>> answers = new HashMap<Step, Map<Choose, String>>() {{
        put(Step.FIRST, new HashMap<Choose, String>() {{
            put(Choose.WIN, "Принять вызов.");
            put(Choose.LOOSE, "Отклонить вызов.");
        }});
        put(Step.SECOND, new HashMap<Choose, String>() {{
            put(Choose.WIN, "Подняться на мостик.");
            put(Choose.LOOSE, "Отказаться подниматься на мостик.");
        }});
        put(Step.THIRD, new HashMap<Choose, String>() {{
            put(Choose.LOOSE, "Рассказать правду о себе.");
            put(Choose.WIN, "Солгать о себе.");
        }});
    }};

    public List<String> getAnswersForCurrentStep(Step step) {
        List<String> answersList = new ArrayList<>();

        for (Map.Entry<Step, Map<Choose, String>> answerFromMap : answers.entrySet()) {
            if (answerFromMap.getKey() == step) {
                Map<Choose, String> map = answerFromMap.getValue();
                for (Map.Entry<Choose, String> map1 : map.entrySet()) {
                    answersList.add(map1.getValue());
                }
            }
        }
        return answersList;
    }

    public Map<Step, Map<Choose, String>> getAllAnswersFromMap() {
        return answers;
    }
}