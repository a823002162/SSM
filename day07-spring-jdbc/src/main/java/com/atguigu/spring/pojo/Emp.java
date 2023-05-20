package com.atguigu.spring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/19/2023 7:54 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer empId;
    private String empName;
    private Double empSalary;
}
