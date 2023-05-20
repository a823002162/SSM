package com.atguigu.spring.dao;

import com.alibaba.druid.support.json.JSONUtils;
import com.atguigu.spring.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/19/2023 8:28 PM
 */
@Repository
public class EmpDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //增
    public void addEmp(Emp emp){
        String sql = "insert into t_emp(emp_name,emp_salary) values(?,?)";
        int update = jdbcTemplate.update(sql, emp.getEmpName(), emp.getEmpSalary());
    }
    //删
    public void deleteEmpById(Integer empId){
        String sql = "delete from t_emp where emp_id = ?";
        int update = jdbcTemplate.update(sql, empId);
    }
    //改姓名
    // @Transactional(timeout = 3)
    public void updateEmpNameById(String empName,Integer empId){
        String sql = "update t_emp set emp_name = ? where emp_id = ?";
        int update = jdbcTemplate.update(sql, empName, empId);
        //手动增加异常,让该方法可以回滚
        // System.out.println(1/0);
    }
    //改薪资
    // @Transactional(timeout = 3)
    public void updateEmpSalaryById(Double empSalary,Integer empId) {
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        String sql = "update t_emp set emp_salary = ? where emp_id = ?";
        int update = jdbcTemplate.update(sql, empSalary, empId);
        //手动增加一个异常,好让数据回滚
        System.out.println(1/0);
        //创建一个编译时异常,默认情况下事务不会rollback

        // FileInputStream fileInputStream = new FileInputStream("bbb.txt");

    }
    //查询姓名
    public String getEmpNameById(Integer empId){
        String sql = "select emp_name from t_emp where emp_id = ?";
        String empName = jdbcTemplate.queryForObject(sql, String.class,empId);
        return empName;
    }
    //查单个
    public Emp selectEmpById(Integer empId){
        BeanPropertyRowMapper<Emp> empMapper = new BeanPropertyRowMapper<>(Emp.class);
        String sql = "select * from t_emp where emp_id = ?";
        Emp emp = jdbcTemplate.queryForObject(sql, empMapper, empId);
        return emp;
    }
    //查多个实体类对象
    @Transactional(readOnly = true)
    public List<Emp> selectEmpAll(){
        BeanPropertyRowMapper<Emp> empMapper = new BeanPropertyRowMapper<>(Emp.class);
        String sql = "select * from t_emp";
        List<Emp> empList = jdbcTemplate.query(sql, empMapper);
        return empList;
    }
}
