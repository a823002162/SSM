package com.atguigu.mapper;

import com.atguigu.pojo.Customer;

public interface CustomerMapper {
    Customer getCustomerWithOrder(Integer customerId);
    //下面的是分步查询相关的
    Customer getCustomerById(Integer customerId);
}
