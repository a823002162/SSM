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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/12/2023 4:28 PM
 */
@Slf4j
public class TestEmployee {
    SqlSession sqlSession;
    EmployeeMapper empMapper;

    @Before
    public void init() throws IOException {
        String config = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(config);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        //创建sqlSession并且开启自动提交
        sqlSession = build.openSession(true);
        //获取代理对象
        empMapper = sqlSession.getMapper(EmployeeMapper.class);

    }
    @After
    public void destroy(){
        sqlSession.close();
    }
    //测试传入单个简单参数
    @Test
    public void test1(){
        /*log.info(sqlSession.toString());
        log.info(empMapper.toString());*/
        Employee employeeById = empMapper.getEmployeeById(1);
        log.info(employeeById.toString());
    }
    //测试传入实体类对象
    @Test
    public void test2(){
        Employee employee = new Employee(null,"jack",3000.0);
        empMapper.addEmployee(employee);
        Employee emp = empMapper.getEmployeeById(employee.getEmpId());
        log.info(emp.toString());
    }
    //测试传入零散的简单参数,方式1
    @Test
    public void test3(){
        empMapper.addEmployeeByArgs("jruy",53432d);
    }

    //测试传入零散的简单参数,方式2
    @Test
    public void test4(){
        empMapper.addEmployeeByParam("candy",33434d);
    }
    //测试map传参数
    @Test
    public void test5(){
        Map<String,Object> map = new HashMap<>();
        map.put("ename","jjj");
        map.put("salary",333d);
        empMapper.addEmployeeByMap(map);
    }
    @Test
    public void test6(){
        Map<String,Object> map = new HashMap<>();
        map.put("eName","ddd");
        map.put("eSalary",333d);
        map.put("eId",5);
        empMapper.editEmployeeByMap(map);

    }
    //测试返回值类型为map
    @Test
    public void test7(){
        Map<String, Object> map = empMapper.getEmployeeAndSalary();
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey()+" = "+entry.getValue());
        }
    }
    //测试返回List集合
    @Test
    public void test8(){
        List<Map<String, Object>> maps = empMapper.selectAll();
        for (Map<String, Object> map : maps) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                System.out.print(entry.getKey()+" = "+entry.getValue());
            }
            System.out.println();
        }
    }
    //测试自定义标签对应关系resultMap
    @Test
    public void test9(){
        List<Employee> employees = empMapper.selectAllResultMap();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
