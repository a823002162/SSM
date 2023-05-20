package com.atguigu.test;

import com.atguigu.config.SpringConfig;
import com.atguigu.controller.PersonController;
import com.atguigu.dao.PersonDao;
import com.atguigu.pojo.Car;
import com.atguigu.pojo.Person;
import com.atguigu.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 7:29 PM
 */
public class TestAnnotation {
    //测试扫描的几种方式
    @Test
    public void test1(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person1 = ioc.getBean(Person.class);
        System.out.println(person1);
        PersonDao personDao = ioc.getBean(PersonDao.class);
        System.out.println("personDao = " + personDao);
        PersonService personService = ioc.getBean(PersonService.class);
        System.out.println("personService = " + personService);
        PersonController personController = ioc.getBean(PersonController.class);
        System.out.println("personController = " + personController);
    }
    //测试自动装配
    @Test
    public void test2(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonController personController = ioc.getBean(PersonController.class);
        personController.sayHi();
    }
    //测试完全注解开发
    @Test
    public void test3(){
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        Car car1 = ioc.getBean("car1",Car.class);
        System.out.println("car1 = " + car1);

        Car car3 = ioc.getBean("car3", Car.class);
        System.out.println("car3 = " + car3);

        Car car2 = ioc.getBean("car2", Car.class);
        System.out.println("car2 = " + car2);
    }
}
