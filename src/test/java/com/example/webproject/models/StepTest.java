package com.example.webproject.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepTest {

    @Test
    void whenCallReturnNumericStep_ThenReturnValueOfStep() {
        int expected = 1;
        int actual = Step.FIRST.returnNumericStep();

        assertEquals(expected, actual);
    }

    @Test
    void whenCallNext_ThenReturnNextStep() {
        Step expected = Step.SECOND;
        Step actual = Step.FIRST.next();

        assertEquals(expected, actual);
    }
}