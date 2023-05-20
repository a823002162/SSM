package com.atguigu.springxml.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/17/2023 8:38 PM
 */
public class ReferenceAspect {
    //切入点表达式重用
    @Pointcut(value = "execution(public int com.atguigu.springxml.calculator.impl.CalculatorPureImpl.add(int,int))")
    public void refCutMethod(){}
}
