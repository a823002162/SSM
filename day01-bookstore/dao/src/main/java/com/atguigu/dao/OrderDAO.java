package com.atguigu.dao;

import com.atguigu.bean.Order;

import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/6/2023 3:51 PM
 */
public interface OrderDAO {
    void addOrder(Order order) throws SQLException;
    int insertOrder(Order order) throws SQLException;
}
