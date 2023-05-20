package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDAO;
import com.atguigu.dao.UserDAO;

import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/23/2023 9:10 PM
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
    //addUser是用来注册的时候往数据库中插入用户用的
    @Override
    public boolean addUser(User user) throws SQLException {
        return update("insert into t_user values(0,?,?,?)",user.getUsername(),user.getPassword(),user.getEmail());
    }

    //getUserByUsernameAndPassword这个方法是登录的时候用来校验登录信息的
    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        return getBean("select user_id id,user_name username,user_pwd password,user_email email from t_user where user_name = ? and user_pwd = ?",User.class,username,password);
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {

        return getBean("select user_id id,user_name username from t_user where user_name = ? ",User.class,username);
    }
}
