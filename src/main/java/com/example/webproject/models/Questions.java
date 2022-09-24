package com.example.webproject.models;

import java.util.HashMap;
import java.util.Map;

public class Questions {

    Map<Step, String> questions = new HashMap<Step, String>() {{
        put(Step.FIRST, "Ты потерял память. Принять вызов НЛО?");
        put(Step.SECOND, "Ты принял вызов. Поднимаешься на мостик к капитану?");
        put(Step.THIRD, "Ты поднялся на мостик. Ты кто?");
    }};

    public String getQuestionsForCurrentStep(Step step) {
        String question = null;

        for (Map.Entry<Step, String> questionFromMap : questions.entrySet()) {
            if (questionFromMap.getKey() == step) {
                question = questionFromMap.getValue();
            }
        }
        return question;
    }
}
