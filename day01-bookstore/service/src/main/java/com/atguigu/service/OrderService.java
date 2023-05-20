package com.atguigu.service;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;

import java.sql.SQLException;

public interface OrderService {
    String addOrder(Cart cart, User user) throws SQLException;
}
