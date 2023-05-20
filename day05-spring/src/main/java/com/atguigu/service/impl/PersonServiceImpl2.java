package com.atguigu.service.impl;

import com.atguigu.dao.PersonDao;
import com.atguigu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 8:13 PM
 */
@Service(value="personService2")
public class PersonServiceImpl2 implements PersonService {
    @Autowired
    private PersonDao personDao;
    @Override
    public void sayHi() {
        personDao.sayHi();
    }
}
