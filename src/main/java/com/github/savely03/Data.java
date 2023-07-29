package com.github.savely03;

import com.beust.jcommander.Parameter;

public class Data {

    @Parameter(names = {"a1", "-arg1"}, required = true)
    private int arg1;
    @Parameter(names = {"a2", "-arg2"}, required = true)
    private int arg2;
    @Parameter(names = {"o", "-operation"}, required = true)
    private String operation;

    public int getArg1() {
        return arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public String getOperation() {
        return operation;
    }
}
