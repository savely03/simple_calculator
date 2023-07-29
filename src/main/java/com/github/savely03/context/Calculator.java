package com.github.savely03.context;

import com.github.savely03.strategy.CalculatorStrategy;

public class Calculator {

    private CalculatorStrategy calculatorStrategy;

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    public double evaluate(int arg1, int arg2) {
        return calculatorStrategy.calculate(arg1, arg2);
    }
}
