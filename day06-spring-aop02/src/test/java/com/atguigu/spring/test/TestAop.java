package com.atguigu.spring.test;

import com.atguigu.spring.calculator.Calculator;
import com.atguigu.spring.calculator.impl.CalculatorPureImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/17/2023 7:57 PM
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAop {
    @Autowired
    // CalculatorPureImpl calculatorPure;
    Calculator calculator;
    @Test
    public void test1(){

        calculator.add(1,2);
        calculator.sub(10,2);
        calculator.mul(3,2);
        calculator.div(10,5);

        /*calculatorPure.add(1,2);
        calculatorPure.sub(10,2);
        calculatorPure.mul(3,2);
        calculatorPure.div(12,3);*/
    }
}
