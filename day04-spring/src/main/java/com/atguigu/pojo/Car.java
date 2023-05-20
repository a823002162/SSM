package com.atguigu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/15/2023 6:52 PM
 */

@AllArgsConstructor

public class Car {
    private Integer id;
    private String brandName;

    public Car() {
        System.out.println("要创建car了");
    }

    public void carInitMethod(){
        System.out.println("Car进行初始化");
    }
    public void carDestroyMethod(){
        System.out.println("car对象销毁");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        System.out.println("要设置car属性了");
        this.brandName = brandName;
    }
}
