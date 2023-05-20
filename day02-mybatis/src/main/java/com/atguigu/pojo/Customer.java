package com.atguigu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/12/2023 7:48 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Customer {
    private Integer customerId;
    private String customerName;
    private List<Order> orderList;
}
