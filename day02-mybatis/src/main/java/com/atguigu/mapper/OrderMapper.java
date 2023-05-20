package com.atguigu.mapper;

import com.atguigu.pojo.Order;

public interface OrderMapper {
    Order getOrderWithCustomer(Integer orderId);

    //分步查询订单order相关的方法
    Order getOrderByCustomerId(Integer customerId);
}
