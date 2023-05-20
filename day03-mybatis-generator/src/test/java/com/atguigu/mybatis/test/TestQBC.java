package com.atguigu.mybatis.test;

import com.atguigu.mybatis.entity.Address;
import com.atguigu.mybatis.entity.Employee;
import com.atguigu.mybatis.entity.EmployeeExample;
import com.atguigu.mybatis.mapper.EmployeeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/14/2023 1:53 PM
 */
@Slf4j
public class TestQBC {
    SqlSession sqlSession;
    EmployeeMapper empMapper;

    @Before
    public void init() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        sqlSession = build.openSession();
        empMapper = sqlSession.getMapper(EmployeeMapper.class);
    }
    @Test
    public void test1(){
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
        EmployeeExample.Criteria criteria2 = employeeExample.or();

        // criteria1.andEmpIdBetween(1, 3);
        criteria1.andEmpIdLessThan(5).andEmpNameIn(Arrays.asList("jack","tom2","sara","mark2"));
        criteria2.andEmpSalaryGreaterThan(2000d);

        List<Employee> employees = empMapper.selectByExample(employeeExample);
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }
    //测试自定义类型转换器
    @Test
    public void test2(){
        Address address = empMapper.getAddressById(1);
        System.out.println(address);
    }
    //测试mybatis分页显示插件
    @Test
    public void test3(){
        //获取查询结果之前,先让数据经过PageHelper插件
        //pageNum当前页码,pageSize每一页显示的行数
        PageHelper.startPage(1, 2);//limit 1,2

        List<Employee> employees = empMapper.selectByExample(null);

        //使用pageInfo计算分页的相关信息
        PageInfo<Employee> pageInfo = new PageInfo<>(employees, 5);
        System.out.println("pageInfo = " + pageInfo);
        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
