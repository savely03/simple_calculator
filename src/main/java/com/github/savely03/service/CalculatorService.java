package com.github.savely03.service;

import com.github.savely03.Data;
import com.github.savely03.context.Calculator;
import com.github.savely03.strategy.Definer;
public class CalculatorService {

    private final Calculator calculator;
    private final Definer definer;

    public CalculatorService(Calculator calculator, Definer definer) {
        this.calculator = calculator;
        this.definer = definer;
    }

    public double invokeAndCalculate(Data data) {
        calculator.setCalculatorStrategy(definer.defineStrategyByOperation(data.getOperation()));
        return calculator.evaluate(data.getArg1(), data.getArg2());
    }
}
