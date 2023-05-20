package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDAO;
import com.atguigu.dao.impl.UserDAOImpl;
import com.atguigu.service.UserService;
import com.atguigu.tools.MD5Util;

import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 4/23/2023 9:01 PM
 */
public class UserServiceImpl implements UserService {
    //创建UserDAO对象,用来执行调用往数据库中插入用户和登录校验用的方法
    private UserDAO dao = new UserDAOImpl();
    @Override
    public boolean registry(User user) throws SQLException {
        //先对密码进行MD5加密
        user.setPassword(MD5Util.encode(user.getPassword()));
        //然后将密码加密过的user再添加到数据库中
        return dao.addUser(user);
    }

    @Override
    public User login(String username, String password) throws SQLException {
        //同样的,查询用户名和密码的时候也要加密后再去数据库中进行查询
        return dao.getUserByUsernameAndPassword(username,MD5Util.encode(password));
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        return dao.getUserByUsername(username);
    }
}
