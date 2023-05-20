package com.atguigu.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.atguigu.factorybean.MyFactoryBean;
import com.atguigu.pojo.Car;
import com.atguigu.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/15/2023 7:33 PM
 */
@Slf4j
public class TestBean {
    //获取ioc容器中bean对象方式1
    @Test
    public void test1(){
        //1.读取配置文件,创建ioc容器(配置文件中的内容会被ioc容器管理)
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取bean对象
        Person person1 = (Person) ioc.getBean("person1");
        System.out.println(person1);

    }
    //获取ioc容器中bean对象方式2
    @Test
    public void test2(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person bean = ioc.getBean(Person.class);
    }
    //获取ioc容器中bean对象方式3 && 获取外部bean
    @Test
    public void test3(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person2 = ioc.getBean("person2", Person.class);
        System.out.println("person2 = " + person2);
    }
    //获取内部bean
    @Test
    public void test4(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person3 = ioc.getBean("person3", Person.class);
        System.out.println("person3 = " + person3);
    }
    //数据源
    @Test
    public void test5() throws SQLException {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beanDataSorce.xml");
        DruidDataSource dataSource = ioc.getBean("dataSource", DruidDataSource.class);
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
    }
    //测试级联属性
    @Test
    public void test6(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beanTest.xml");
        Person person1 = ioc.getBean("person1", Person.class);
        System.out.println("person1 = " + person1);
    }
    //测试构造器注入
    @Test
    public void test7(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person4 = ioc.getBean("person4", Person.class);
        System.out.println("person4 = " + person4);
        // person4.setId(4);
        Integer id = person4.getId();
    }
    //构造器注入name方式
    @Test
    public void test71(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car4 = ioc.getBean("car4", Car.class);
        System.out.println("car4 = " + car4);
    }
    //构造器注入下标方式
    @Test
    public void test72(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car5 = ioc.getBean("car5", Car.class);
        System.out.println("car5 = " + car5);
    }
    //构造器注入类型的方式
    @Test
    public void test73(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car6 = ioc.getBean("car6", Car.class);
        System.out.println("car6 = " + car6);
    }
    //特殊值处理1
    @Test
    public void test8(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person5 = ioc.getBean("person5", Person.class);
        System.out.println("person5 = " + person5);
    }
    //特殊值处理2
    @Test
    public void test9(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person6 = ioc.getBean("person6", Person.class);
        System.out.println("person6 = " + person6);
    }
    //p空间赋值
    @Test
    public void test10(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car3 = ioc.getBean("car3", Car.class);
        System.out.println("car3 = " + car3);
    }
    //自动装配
    @Test
    public void test11(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person8 = ioc.getBean("person8", Person.class);
        System.out.println("person8 = " + person8);
    }

    //集合类型的bean
    @Test
    public void test12(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        // List<Car> carList = (List<Car>) ioc.getBean("carList");
        List<Car> carList = ioc.getBean("carList", List.class);
        for (Car car : carList) {
            System.out.println("car = " + car);
        }
    }
    //测试factoryBean的使用
    @Test
    public void test13(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        Car car1 = (Car) ioc.getBean("myFactoryBean");
        Car car2 = (Car) ioc.getBean("myFactoryBean");
        System.out.println("car = " + car1);
        System.out.println("car2 = " + car2);
        System.out.println(car1 == car2);
    }

    @Test
    public void test14(){
        ConfigurableApplicationContext ioc = new ClassPathXmlApplicationContext("MyPostProcessor.xml");
        Car car = ioc.getBean("car", Car.class);
        ioc.close();
    }

}
