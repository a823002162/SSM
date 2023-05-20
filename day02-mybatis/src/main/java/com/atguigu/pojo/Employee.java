package com.atguigu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/12/2023 4:26 PM
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private Integer empId;
    private String empName;
    private Double empSalary;
}
