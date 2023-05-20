package com.atguigu.factorybean;

import com.atguigu.pojo.Car;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 6:11 PM
 */
@Data
public class MyFactoryBean implements FactoryBean<Car>{

    // private String FactoryName;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
        // return false;
    }
}
