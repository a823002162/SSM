package com.atguigu.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.sql.Time;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/19/2023 9:20 PM
 */
@Aspect
@Component
@Slf4j
public class TimeAscept {
    // @Around(value = "execution(* *..*.*(..))")
    @Around(value = "execution(* com.atguigu.spring.dao.EmpDao.selectEmpAll(..))")
    public Object AroundMethod(ProceedingJoinPoint joinPoint){
        //获取方法名
        String name = joinPoint.getSignature().getName();
        //获取参数
        Object[] args = joinPoint.getArgs();
        long before = 0;
        long after = 0;
        Object result;
        try {
            //获取方法开始前的时间戳
            before = System.currentTimeMillis();
            //前置通知
            log.info("[前置通知]"+name+"方法开始运行");
            //执行方法
            result = joinPoint.proceed(args);
            //记录方法执行完的时间戳
            after = System.currentTimeMillis();
        } catch (Throwable e) {
            log.info("[异常通知]"+name+"出异常了");
            throw new RuntimeException(e);
        } finally {
            log.info("[后置通知]"+"运行时间为:"+(after-before));
        }
        return result;
    }
}
