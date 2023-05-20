package com.atguigu.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/17/2023 6:36 PM
 */
public class CglibDynamicProxy<T> {
    private T target;
    public CglibDynamicProxy(T target){
        this.target = target;
    }
    public Object getProxy(){
        //获取增强器
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        //获取拦截器
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                try {
                    System.out.println("前置通知:"+method.getName()+",参数:"+ Arrays.asList(args));
                    //调用目标方法
                    Object result = method.invoke(target, args);
                    System.out.println("返回通知:"+method.getName()+",运行结果是:"+result);
                    return result;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("异常通知:"+method.getName()+",出异常了");
                    throw new RuntimeException(e);
                } finally {
                    System.out.println("获知通知:"+method.getName()+",运行结束");
                }
            }
        });

        //创建代理对象
        T proxy = (T) enhancer.create();
        return proxy;
    }
}
