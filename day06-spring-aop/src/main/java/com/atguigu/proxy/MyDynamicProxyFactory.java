package com.atguigu.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/17/2023 4:50 PM
 */
@Slf4j
public class MyDynamicProxyFactory {
    //被代理的对象
    private Object target;

    //构造器
    public MyDynamicProxyFactory(Object target){
        this.target = target;
    }

    //生产代理对象的方法
    public Object getProxy(){
        //生产代理对象参数1: 目标类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        //生产代理对象参数2: 目标类实现的接口数组
        Class<?>[] interfaces = target.getClass().getInterfaces();
        //生产代理对象参数3: handler --> 再方法中以匿名内部类的形式提供

        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //先来一个结果
                Object result = null;
                //前置通知
                try {
                    System.out.println("前置通知"+ method.getName()+"方法开始了,参数是:"+ Arrays.asList(args));
                    result = method.invoke(target, args);
                    System.out.println("返回通知"+method.getName()+"方法结果是:"+result);
                    return result;
                } catch (Exception e) {
                    System.out.println("异常通知,"+method.getName()+"执行过程中出问题了");
                    e.printStackTrace();
                    // throw new RuntimeException(e);
                } finally {
                    System.out.println("后置通知"+method.getName()+"方法结束了");
                }
                return result;
            }
        });
        return proxy;
    }
}
