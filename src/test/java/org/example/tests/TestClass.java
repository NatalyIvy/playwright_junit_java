package org.example.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Execution(ExecutionMode.CONCURRENT)
public class TestClass {

    @ParameterizedTest
    @ValueSource(strings = {"nothing", "what does it mean", "What a day!"})
    void parametrized(String candidate) {
        Assertions.assertTrue(candidate.contains("what"));
    }

    @Test
    void oneSecondTest() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    void twoSecondTest() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    void threeSecondTest() throws InterruptedException {
        Thread.sleep(3000);
    }
}