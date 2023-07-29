package com.github.savely03.service;

import com.beust.jcommander.JCommander;
import com.github.savely03.Data;
import com.github.savely03.context.Calculator;
import com.github.savely03.exception.DefineOperationException;
import com.github.savely03.exception.DivideOnZeroException;
import com.github.savely03.strategy.Definer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CalculatorServiceTest {
    private static CalculatorService calculatorService;

    @BeforeAll
    static void setUp() {
        calculatorService = new CalculatorService(new Calculator(), new Definer());
    }

    static Stream<Arguments> provideArguments() {
        return Stream.of(
            Arguments.of("5", "2", "sub", 3),
            Arguments.of("10", "5", "div", 2),
            Arguments.of("1", "5", "mul", 5),
            Arguments.of("4", "2", "sum", 6),
            Arguments.of("0", "5", "sub", -5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void invokeAndCalculateTest(String arg1, String arg2, String operation, int expected) {
        Data data = generateData(arg1, arg2, operation);

        assertThat(calculatorService.invokeAndCalculate(data)).isEqualTo(expected);
    }

    @Test
    void invokeAndCalculateTestWhenDivideOnZeroExceptionTest() {
        Data data = generateData("5", "0", "div");

        assertThatExceptionOfType(DivideOnZeroException.class).isThrownBy(
            () -> calculatorService.invokeAndCalculate(data)
        );
    }

    @Test
    void invokeAndCalculateWhenDefineOperationExceptionTest() {
        Data data = generateData("0", "0", "aaa");

        assertThatExceptionOfType(DefineOperationException.class).isThrownBy(
            () -> calculatorService.invokeAndCalculate(data)
        );
    }

    private Data generateData(String arg1, String arg2, String operation) {
        String[] args = {"-arg1", arg1, "-arg2", arg2, "-operation", operation};
        Data data = new Data();
        JCommander.newBuilder().addObject(data).args(args).build();
        return data;
    }
}
