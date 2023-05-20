package com.atguigu.calculator.impl;

import com.atguigu.calculator.Calculator;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 10:00 PM
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int division(int a, int b) {
        return a / b;
    }
}
