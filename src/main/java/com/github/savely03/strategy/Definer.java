package com.github.savely03.strategy;

import com.github.savely03.exception.DefineOperationException;
import com.github.savely03.strategy.impl.Division;
import com.github.savely03.strategy.impl.Multiplication;
import com.github.savely03.strategy.impl.Subtraction;
import com.github.savely03.strategy.impl.Summation;

public class Definer {
    public CalculatorStrategy defineStrategyByOperation(String operation) {
        return switch (operation) {
            case "sum" -> new Summation();
            case "sub" -> new Subtraction();
            case "mul" -> new Multiplication();
            case "div" -> new Division();
            default -> throw new DefineOperationException(operation);
        };
    }
}
