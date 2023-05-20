package com.atguigu.calculator;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/17/2023 6:48 PM
 */
// @Component
public class CalculatorNoImpl {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }


    public int division(int a, int b) {
        return a / b;
    }
}
