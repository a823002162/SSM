package com.atguigu.test;

import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/14/2023 10:49 AM
 */
/*
* - 不是同一个SqlSession
- 同一个SqlSession但是查询条件发生了变化
- 同一个SqlSession两次查询期间执行了任何一次增删改操作
- 同一个SqlSession两次查询期间手动清空了缓存
- 同一个SqlSession两次查询期间提交了事务
* */
@Slf4j
public class TestCache {
    SqlSessionFactory build;
    @Before
    public void init() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        build = sqlSessionFactoryBuilder.build(resourceAsStream);
    }
    //不是同一个SqlSession
    @Test
    public void test1(){
        SqlSession sqlSession1 = build.openSession();
        EmployeeMapper employeeMapper1 = sqlSession1.getMapper(EmployeeMapper.class);

        SqlSession sqlSession2 = build.openSession();
        EmployeeMapper employeeMapper2 = sqlSession2.getMapper(EmployeeMapper.class);

        List<Employee> employees = employeeMapper1.selectAllEmpWithCache();
        List<Employee> employees1 = employeeMapper1.selectAllEmpWithCache();
    }
    //同一个SqlSession但是查询条件发生了变化
    @Test
    public void test2(){
        SqlSession sqlSession1 = build.openSession();
        EmployeeMapper employeeMapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        List<Map<String, Object>> maps = employeeMapper1.selectAll();
        List<Employee> employees = employeeMapper1.selectAllEmpWithCache();
    }

    //同一个SqlSession两次查询期间执行了任何一次增删改操作
    @Test
    public void test3(){
        SqlSession sqlSession1 = build.openSession();
        EmployeeMapper employeeMapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        List<Employee> employees1 = employeeMapper1.selectAllEmpWithCache();
        employeeMapper1.updateEmployeeDynamic(new Employee(5,"wong2",6000d));
        List<Employee> employees2 = employeeMapper1.selectAllEmpWithCache();

        sqlSession1.commit();
        sqlSession1.close();
    }

    //同一个SqlSession两次查询期间手动清空了缓存
    @Test
    public void test4(){
        SqlSession sqlSession1 = build.openSession();
        EmployeeMapper employeeMapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        List<Employee> employees1 = employeeMapper1.selectAllEmpWithCache();

        sqlSession1.clearCache();

        List<Employee> employees2 = employeeMapper1.selectAllEmpWithCache();

        sqlSession1.commit();
        sqlSession1.close();
    }
    //同一个SqlSession两次查询期间提交了事务
    @Test
    public void test5(){
        SqlSession sqlSession1 = build.openSession();
        EmployeeMapper employeeMapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        List<Employee> employees1 = employeeMapper1.selectAllEmpWithCache();

        sqlSession1.commit();

        List<Employee> employees2 = employeeMapper1.selectAllEmpWithCache();

        // sqlSession1.commit();
        sqlSession1.close();
    }
    //测试二级缓存
    @Test
    public void test6(){
        SqlSession sqlSession1 = build.openSession();
        EmployeeMapper employeeMapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        List<Employee> employees1 = employeeMapper1.selectAllEmpWithCache();

        //要提交,否则二级缓存中没有内容,最终还得去一级缓存中找
        sqlSession1.commit();

        List<Employee> employees2 = employeeMapper1.selectAllEmpWithCache();
        List<Employee> employees3 = employeeMapper1.selectAllEmpWithCache();
        List<Employee> employees4 = employeeMapper1.selectAllEmpWithCache();
        List<Employee> employees5 = employeeMapper1.selectAllEmpWithCache();
        List<Employee> employees6 = employeeMapper1.selectAllEmpWithCache();
        List<Employee> employees7 = employeeMapper1.selectAllEmpWithCache();
        List<Employee> employees8 = employeeMapper1.selectAllEmpWithCache();
        List<Employee> employees9 = employeeMapper1.selectAllEmpWithCache();

        // sqlSession1.commit();
        sqlSession1.close();
    }
}
