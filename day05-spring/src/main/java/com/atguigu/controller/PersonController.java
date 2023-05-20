package com.atguigu.controller;

import com.atguigu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 7:22 PM
 */
@Controller
public class PersonController {
    @Autowired
    @Qualifier(value = "personService1")
    // private PersonService personService;
    private PersonService personServiceImpl10;
    public void sayHi(){
        // personService.sayHi();
        personServiceImpl10.sayHi();
    }
}
