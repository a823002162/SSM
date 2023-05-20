package com.atguigu.test;

import com.atguigu.controller.PersonController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 9:27 PM
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestComponent {
    @Autowired
    private PersonController personController;
    @Test
    public void test1(){
        personController.sayHi();
    }
}
