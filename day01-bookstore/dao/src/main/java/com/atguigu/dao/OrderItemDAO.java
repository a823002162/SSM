package com.atguigu.dao;

import java.sql.SQLException;

public interface OrderItemDAO {
   void addOrderItem(Object[][]params) throws SQLException;
}
