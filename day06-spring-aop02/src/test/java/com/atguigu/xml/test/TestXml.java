package com.atguigu.xml.test;

import com.atguigu.springxml.calculator.Calculator;
import com.atguigu.springxml.calculator.impl.CalculatorPureImpl;
import com.atguigu.springxml.calculator.impl.TestCalculator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/19/2023 6:13 PM
 */
@ContextConfiguration(locations = "classpath:SpringXml.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestXml {
    @Autowired
    // private CalculatorPureImpl calculatorPureImpl;
    private Calculator calculator;
    @Autowired
    private TestCalculator testCalculator;

    @Test
    public void test1(){
        // System.out.println(calculatorPureImpl);
        // calculatorPureImpl.add(1,2);
        calculator.add(1,3);
        testCalculator.div(10,2);
    }
}
