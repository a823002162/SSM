package com.atguigu.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 7:21 PM
 */
@Repository
public class PersonDao {
    public void sayHi(){
        System.out.println("装配成功,我是dao里面的sayhi方法");
    }
}
