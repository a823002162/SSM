package com.atguigu.spring.service;

import com.atguigu.spring.dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/19/2023 8:28 PM
 */
@Service
public class EmpService {
    @Autowired
    private EmpDao empDao;
    //一个service中的方法同时调用两个dao中的方法
    @Transactional(timeout = 3,rollbackFor = FileNotFoundException.class)
    public void updateTwice(String empName,Integer empId1,Double empSalary,Integer empId2) throws FileNotFoundException {
        //同时执行两个方法,观察在失败的情况下是否会回滚
        //修改员工的姓名
        empDao.updateEmpNameById(empName,empId1);
        //修改员工的薪资
        empDao.updateEmpSalaryById(empSalary,empId2);
    }
    //测试事务的隔离级别
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String getEmpName(Integer empId){
        String empNameById = empDao.getEmpNameById(empId);
        return empNameById;
    }
    //测试事务的传播
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateEmpName(Integer empId,String empName){
        empDao.updateEmpNameById(empName,empId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateEmpSalary(Integer empId,Double empSalary){
        empDao.updateEmpSalaryById(empSalary,empId);
    }
}
