package com.atguigu.dao.impl;

import com.atguigu.dao.BaseDAO;
import com.atguigu.dao.OrderItemDAO;

import java.sql.SQLException;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/6/2023 4:45 PM
 */
public class OrderItemDAOImpl extends BaseDAO implements OrderItemDAO {
    @Override
    public void addOrderItem(Object[][]params) throws SQLException {
        boolean update = update("insert into t_order_item values (0,?,?,?,?,?,?)", params);
    }
}
