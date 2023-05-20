package com.atguigu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/12/2023 7:51 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Order {
    private Integer orderId;
    private String orderName;
    private Customer customer;
}
