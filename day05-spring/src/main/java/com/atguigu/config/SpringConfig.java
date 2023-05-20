package com.atguigu.config;

import com.atguigu.pojo.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/16/2023 8:51 PM
 */
@Configuration
@ComponentScan(basePackages = "com.atguigu")
public class SpringConfig {
    @Bean
    public Car car1(){
        Car car = new Car(1,"tesla");
        return car;
    }
    @Bean(value="car3")
    public Car car2(){
        Car car = new Car(2,"Benz");
        return car;
    }
}
