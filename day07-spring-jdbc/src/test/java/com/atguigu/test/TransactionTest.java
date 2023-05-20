package com.atguigu.test;

import com.atguigu.spring.dao.EmpDao;
import com.atguigu.spring.pojo.Emp;
import com.atguigu.spring.service.EmpService;
import com.atguigu.spring.service.TopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/19/2023 8:24 PM
 */
@ContextConfiguration(value= "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionTest {
    //装配数据库连接池
    @Autowired
    private DataSource dataSource;
    //装配jdbcTemplate bean对象
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EmpDao empDao;
    @Autowired
    private EmpService empService;
    @Autowired
    private TopService topService;
    //调用service中的方法来测试事务
    @Test
    public void test() throws FileNotFoundException {
        String empName = "王五3";
        Integer empId1 = 3;
        Double empSalary = 10020d;
        Integer empId2 = 4;
        empService.updateTwice(empName,empId1,empSalary,empId2);
    }

    //查询所有实体类对象(验证read-only属性)
    @Test
    public void test6(){
        List<Emp> emps = empDao.selectEmpAll();
        System.out.println(emps);
        for (Emp emp : emps) {
            System.out.println("emp = " + emp);
        }
    }


    //验证事务的隔离级别
    @Test
    public void test7(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                empService.updateEmpName(1,"bbb");
            }
        }).start();
        String empName = empService.getEmpName(1);
        System.out.println("empName = " + empName);
    }
    @Test
    public void test8(){
        empService.updateEmpName(1,"bbb");
    }

    //测试事物的传播
    @Test
    public void test9(){
        topService.topTxMethod();
    }

    //先来测试下DAO有没有问题
    //增
    @Test
    public void test1(){
        Emp emp = new Emp();
        emp.setEmpName("张三");
        emp.setEmpSalary(3000d);
        empDao.addEmp(emp);
    }
    //删
    @Test
    public void test2(){
        empDao.deleteEmpById(14);
    }
    //改姓名
    @Test
    public void test3(){
        empDao.updateEmpNameById("李四",13);
    }
    //改薪资
    @Test
    public void test4() throws FileNotFoundException {
        empDao.updateEmpSalaryById(10000d,13);
    }
    //查询单个实体类对象
    @Test
    public void test5(){
        Emp emp = empDao.selectEmpById(3);
        System.out.println("emp = " + emp);
    }

}
