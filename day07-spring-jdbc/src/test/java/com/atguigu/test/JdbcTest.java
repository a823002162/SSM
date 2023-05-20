package com.atguigu.test;

import com.atguigu.spring.pojo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/19/2023 7:37 PM
 */
@Slf4j
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JdbcTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        System.out.println(jdbcTemplate);
    }

    // 返回单个简单类型
    @Test
    public void test2() {
        String empName = jdbcTemplate.queryForObject("select emp_name from t_emp where emp_id = ?", String.class, 2);
        System.out.println("empName = " + empName);
    }

    // 返回单个实体类型
    @Test
    public void test3() {
        BeanPropertyRowMapper<Emp> empMapper = new BeanPropertyRowMapper<>(Emp.class);
        Emp emp = jdbcTemplate.queryForObject("select * from t_emp where emp_id = ?", empMapper, 3);
        System.out.println("emp = " + emp);
    }

    // 返回多个实体类型参数
    @Test
    public void test4() {
        BeanPropertyRowMapper<Emp> empMapper = new BeanPropertyRowMapper<>(Emp.class);
        List<Emp> query = jdbcTemplate.query("select * from t_emp", empMapper);
        for (Emp emp : query) {
            System.out.println("emp = " + emp);
        }
    }

    // 增删改
    @Test
    public void test5() {
        // 增
        String sql1 = "insert into t_emp(emp_name,emp_salary) values(?,?)";
        int i = jdbcTemplate.update(sql1, "jack11112222", 3000d);
        System.out.println("i = " + i);
        // 删
        String sql2 = "delete from t_emp where emp_id = ?";
        int update = jdbcTemplate.update(sql2, 2);
        System.out.println("update = " + update);
        // 改
        String sql3 = "update t_emp set emp_name = ? , emp_salary= ? where emp_id = ?";
        int gavin222 = jdbcTemplate.update(sql3, "gavin222", 5000d, 12);
        System.out.println("gavin222 = " + gavin222);
    }
}
