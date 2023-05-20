package com.atguigu.spring.calculator.impl;

import com.atguigu.spring.calculator.Calculator;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/17/2023 7:35 PM
 */
@Component
public class CalculatorPureImpl implements Calculator {
    public int add(int a, int b) {
        return a + b;
    }


    public int sub(int a, int b) {
        return a - b;
    }


    public int mul(int a, int b) {
        return a * b;
    }


    public int div(int a, int b) {
        return a / b;
    }
}
