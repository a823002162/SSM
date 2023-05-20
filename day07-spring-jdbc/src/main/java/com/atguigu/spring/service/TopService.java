package com.atguigu.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/19/2023 11:13 PM
 */
@Service
public class TopService {
    @Autowired
    private EmpService empService;

    @Transactional
    public void topTxMethod(){
        empService.updateEmpName(1,"fff");
        //修改薪资的是有异常的,会回滚
        empService.updateEmpSalary(2,100d);
    }
}
