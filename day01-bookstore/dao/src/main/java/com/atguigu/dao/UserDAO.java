package com.atguigu.dao;

import com.atguigu.bean.User;

import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/23/2023 9:11 PM
 */
public interface UserDAO {
    boolean addUser(User user) throws SQLException;
    User getUserByUsernameAndPassword(String username,String password) throws SQLException;

    User getUserByUsername(String username) throws SQLException;
}
