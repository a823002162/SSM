package com.atguigu.service.impl;

import com.atguigu.dao.PersonDao;
import com.atguigu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 8:12 PM
 */
@Service(value="personService1")
public class PersonServiceImpl1 implements PersonService {
    @Autowired
    private PersonDao personDao;
    @Override
    public void sayHi() {
        personDao.sayHi();
    }
}
