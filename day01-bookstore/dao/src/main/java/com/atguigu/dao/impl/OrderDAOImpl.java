package com.atguigu.dao.impl;

import com.atguigu.bean.Order;
import com.atguigu.dao.BaseDAO;
import com.atguigu.dao.OrderDAO;
import com.atguigu.tools.ConnectionTools;

import java.sql.*;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/6/2023 3:51 PM
 */
public class OrderDAOImpl extends BaseDAO implements OrderDAO {
    @Override
    public void addOrder(Order order) throws SQLException {
        update("insert into t_order values(0,?,?,?,?,?,?)",order.getOrderSequence(),order.getCreateTime(),order.getTotalCount(),order.getTotalAmount(),order.getOrderStatus(),order.getUserId());
    }

    public int insertOrder(Order order) throws SQLException {
        //获取connection链接
        Connection connection = ConnectionTools.getConnection();
        String sql = "insert into t_order values(0,?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //补全sql语句中缺少的部分内容,全部使用setObject也行
        pst.setString(1,order.getOrderSequence());
        pst.setObject(2,order.getCreateTime());
        pst.setInt(3,order.getTotalCount());
        pst.setDouble(4,order.getTotalAmount());
        pst.setInt(5,order.getOrderStatus());
        pst.setInt(6,order.getUserId());
        //执行更新动作
        pst.executeUpdate();
        //获取自增长的key
        ResultSet resultSet = pst.getGeneratedKeys();
        int key = 0;
        while (resultSet.next()) {
           key =  resultSet.getInt(1);
        }
        //返回orderId
        return key;
    }
}
