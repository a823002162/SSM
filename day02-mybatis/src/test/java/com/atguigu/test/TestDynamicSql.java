package com.atguigu.test;

import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.pojo.Employee;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/13/2023 7:09 PM
 * 用来测试动态sql语句
 */
@Slf4j
public class TestDynamicSql {
    SqlSession sqlSession;
    EmployeeMapper employeeMapper;

    @Before
    public void init() throws IOException {
        String config = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        sqlSession = build.openSession();
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    }
    @After
    public void destroy(){
        sqlSession.commit();
        sqlSession.close();
    }
    //测试动态sql中的where和if标签
    @Test
    public void test1(){
        Employee employee = new Employee();
        employee.setEmpName("jack");
        // employee.setEmpSalary(2000d);
        List<Employee> employees = employeeMapper.selectEmployeeByCondition(employee);
        for (Employee employee1 : employees) {
            System.out.println(employee1);
        }
    }
    //测试set标签
    @Test
    public void test2(){
        Employee employee = new Employee();
        employee.setEmpName("jack");
        employee.setEmpSalary(5000d);
        employee.setEmpId(2);
        employeeMapper.updateEmployeeDynamic(employee);
    }
    //测试trim标签
    @Test
    public void test3(){
        Employee employee = new Employee();
        employee.setEmpName("jack");
        employee.setEmpSalary(5000d);
        employee.setEmpId(2);
        List<Employee> employees = employeeMapper.selectEmployeeByConditionByTrim(employee);
        for (Employee employee1 : employees) {
            System.out.println("employee1 = " + employee1);
        }
    }
    //测试choose when otherwise标签
    @Test
    public void test4(){
        Employee employee = new Employee();
        employee.setEmpName("jack1");
        employee.setEmpSalary(3500d);
        employee.setEmpId(3);
        List<Employee> employees = employeeMapper.selsectEmployeeByConditionByChoose(employee);
        for (Employee employee1 : employees) {
            System.out.println("employee1 = " + employee1);
        }
    }
    //测试foreach之批量添加
    @Test
    public void test5(){
        List<Employee> empList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmpName("jerry");
        employee1.setEmpSalary(6000d);
        Employee employee2 = new Employee();
        employee2.setEmpName("henry");
        employee2.setEmpSalary(5500d);
        empList.add(employee1);
        empList.add(employee2);
        employeeMapper.addEmployeeByBatch(empList);
    }
    //测试foreach之批量更新
    @Test
    public void test6(){
        List<Employee> empList = new ArrayList<>();
        Employee employee1 = new Employee(1,"mark",4000d);
        Employee employee2 = new Employee(2,"tom2",5000d);
        Employee employee3 = new Employee(3,"stark",7000d);
        Employee employee4 = new Employee(4,"sara",6000d);
        empList.add(employee1);
        empList.add(employee2);
        empList.add(employee3);
        empList.add(employee4);
        employeeMapper.updateEmployeeByBatch(empList);
    }
    //测试foreach之批量查询
    @Test
    public void test7(){
        List<Integer> empList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Employee> employees = employeeMapper.selectEmployeeByBatch(empList);
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }
}
