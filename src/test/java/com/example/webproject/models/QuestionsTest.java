package com.example.webproject.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsTest {
    private Questions questions;

    @BeforeEach
    void init() {
        questions = new Questions();
    }

    @Test
    void whenCallGetQuestionsForCurrentStep_ThenReturnString() {
        String actual = questions.getQuestionsForCurrentStep(Step.FIRST);
        String expected = "test";
        assertEquals(expected.getClass(), actual.getClass());
    }

    @AfterEach
    void destroy() {
        questions = null;
    }
}