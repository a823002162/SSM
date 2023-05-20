package com.atguigu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 10:36 PM
 */
@Data
@AllArgsConstructor

@NoArgsConstructor
public class Soldier {
    private Integer soldierId;
    private String soldierName;
}