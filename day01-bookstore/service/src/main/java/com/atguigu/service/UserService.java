package com.atguigu.service;

import com.atguigu.bean.User;

import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/23/2023 8:56 PM
 */
public interface UserService {
    boolean registry(User user) throws SQLException;
    User login(String username,String password) throws SQLException;

    User getUserByUsername(String username) throws SQLException;
}
