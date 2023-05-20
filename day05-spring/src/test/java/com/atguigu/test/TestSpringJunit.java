package com.atguigu.test;

import com.atguigu.config.SpringConfig;
import com.atguigu.pojo.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 9:18 PM
 */
// @ContextConfiguration(locations="classpath:applicationContext.xml") //读取配置文件
@ContextConfiguration(classes = SpringConfig.class)//读取配置类
@RunWith(SpringJUnit4ClassRunner.class)//自动创建ioc容器
public class TestSpringJunit {
    @Autowired//自动装配car
    private Car car1;
    @Autowired
    @Qualifier(value = "car3")
    private Car car2;
    @Autowired
    private Car car3;

    @Test
    public void test1(){
        System.out.println("car1 = " + car1);
        System.out.println("car2 = " + car2);
        System.out.println("car3 = " + car3);
    }
}
