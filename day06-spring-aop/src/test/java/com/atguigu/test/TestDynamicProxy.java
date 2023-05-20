package com.atguigu.test;

import com.atguigu.calculator.Calculator;
import com.atguigu.calculator.CalculatorNoImpl;
import com.atguigu.congfig.SpringConfig;
import com.atguigu.proxy.*;
import com.atguigu.soldier.SoldierService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 10:16 PM
 */
@ContextConfiguration(classes = SpringConfig.class)//读取配置类
@RunWith(SpringJUnit4ClassRunner.class)//自动创建ioc容器
public class TestDynamicProxy {
    //先拿到一个calculator对象
    @Autowired
    private Calculator calculator;
    @Autowired
    private Calculator calculatorImpl;
    @Autowired
    private SoldierService soldierService;
    // @Autowired
    // private CalculatorNoImpl calculatorNo;

    @Test
    public void test1(){
        //根据calculator对象获取proxyFactory
        LogDynamicProxyFactory<Calculator> proxyFactory = new LogDynamicProxyFactory<>(calculatorImpl);
        //生产代理对象
        Calculator proxy = proxyFactory.getProxy();

        int add = proxy.add(1, 2);
        int subtract = proxy.subtract(5, 2);
        int multiply = proxy.multiply(2, 3);
        int division = proxy.division(10, 2);
    }
    @Test
    public void test2() {
        LogDynamicProxyFactory<SoldierService> soldierServiceLogDynamicProxyFactory = new LogDynamicProxyFactory<>(soldierService);
        SoldierService proxy = soldierServiceLogDynamicProxyFactory.getProxy();
        String soldierNameById = proxy.getSoldierNameById(1);
        System.out.println("soldierNameById = " + soldierNameById);

    }
    @Test
    public void test3() throws IOException {
        String config = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = build.openSession(true);
        SoldierService mapper = sqlSession.getMapper(SoldierService.class);
        // String soldierNameById = mapper.getSoldierNameById(2);
        // System.out.println("soldierNameById = " + soldierNameById);

        //创建工厂
        LogDynamicProxyFactory<SoldierService> soldierServiceLogDynamicProxyFactory = new LogDynamicProxyFactory<>(mapper);

        //创建代理对象
        SoldierService proxy = soldierServiceLogDynamicProxyFactory.getProxy();

        //执行方法
        String soldierNameById = proxy.getSoldierNameById(1);
        System.out.println("soldierNameById = " + soldierNameById);
    }
    //测试静态代理
    @Test
    public void test4(){
        StaticProxy staticProxy = new StaticProxy(calculator);
        int add = staticProxy.add(2, 4);
    }
    //测试动态代理
    @Test
    public void test5(){
        //创建动态代理工厂
        MyDynamicProxyFactory myDynamicProxyFactory = new MyDynamicProxyFactory(calculator);
        //创建动态代理对象
        Calculator proxy = (Calculator) myDynamicProxyFactory.getProxy();
        //通过代理执行对应的方法
        // int add = proxy.add(1, 3);
        int division = proxy.division(10, 0);

    }
    //测试动态代理泛型
    @Test
    public void test6(){
        //创建代理工厂
        MyDynamicProxyFactoryGeneric<Calculator> ProxyFactory = new MyDynamicProxyFactoryGeneric<>(calculator);
        //创建代理对象
        Calculator proxy = ProxyFactory.getProxy();
        //通过代理对象执行对应的方法
        int multiply = proxy.multiply(1, 3);
    }
    //测试Cglib动态代理
    @Test
    public void test7(){
        CalculatorNoImpl calculatorNo = new CalculatorNoImpl();
        //创建动态代理工厂
        CglibDynamicProxy cglibDynamicProxy = new CglibDynamicProxy(calculatorNo);
        //创建动态代理对象
        CalculatorNoImpl proxy = (CalculatorNoImpl) cglibDynamicProxy.getProxy();
        //调用方法
        int multiply = proxy.multiply(2, 3);
    }

}
