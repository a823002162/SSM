package com.atguigu.test;

import com.atguigu.mapper.CustomerMapper;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.pojo.Customer;
import com.atguigu.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/12/2023 7:56 PM
 */
@Slf4j
public class TestCustomerAndOrder {
    SqlSession sqlSession;
    CustomerMapper customerMapper;
    OrderMapper orderMapper;
    @Before
    public void init() throws IOException {
        String config = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        //默认不开启自动提交,后面在destroy方法里面提交
        sqlSession = build.openSession();
        customerMapper = sqlSession.getMapper(CustomerMapper.class);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }
    @After
    public void destroy(){
        //手动提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    //测试一对多的关系,从order 到 customer 是一对一的关系
    @Test
    public void test1(){
        Order orderWithCustomer = orderMapper.getOrderWithCustomer(1);
        System.out.println(orderWithCustomer);
    }
    //测试多对一的关系,从customer 到 order 是一对多的关系
    @Test
    public void test2(){
        Customer c = customerMapper.getCustomerWithOrder(1);
        System.out.println(c.getCustomerId());
        System.out.println(c.getCustomerName());
        for (Order order : c.getOrderList()) {
            System.out.println(order);
        }
    }
    //测试分步查询
    @Test
    public void test10() throws InterruptedException {
        Customer customer = customerMapper.getCustomerById(1);
        log.info(customer.getCustomerName());
        Thread.sleep(3000);
        List<Order> orderList = customer.getOrderList();
        for (Order order : orderList) {
            log.info(order.toString());
        }
    }
}
