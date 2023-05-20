package com.atguigu.congfig;

import com.atguigu.calculator.Calculator;
import com.atguigu.calculator.impl.CalculatorImpl;
import com.atguigu.proxy.LogDynamicProxyFactory;
import com.atguigu.soldier.SoldierService;
import com.atguigu.soldier.impl.SoldierServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 10:17 PM
 */
@Configuration
@ComponentScan(basePackages = "com.atguigu")
public class SpringConfig {
    @Bean
    public Calculator calculator(){
        return new CalculatorImpl();
    }
    @Bean
    public SoldierService soldierService(){
        return new SoldierServiceImpl();
    }
    /*@Bean
    public LogDynamicProxyFactory logDynamicProxyFactory(){
        return new LogDynamicProxyFactory()
    }*/
}
