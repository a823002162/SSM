package com.atguigu.proxy;

import com.atguigu.calculator.Calculator;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/17/2023 4:35 PM
 * 用来测试静态代理
 */
@Slf4j
public class StaticProxy {
    private Calculator calculator;

    public StaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    public int add(int a, int b) {
        log.info("前置通知,add方法开始了" + "参数是:" + a + "," + b);
        int result = 0;
        try {
            result = calculator.add(a, b);
            System.out.println(1 / 0);
            log.info("返回通知,结果是:" + result);
            return result;
        } catch (Exception e) {
            log.info("异常通知: 出异常了");
            throw new RuntimeException(e);
        } finally {
            log.info("后置通知,add方法结束了");
        }
    }
}
