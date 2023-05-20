package com.atguigu.springxml.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/17/2023 7:37 PM
 */
// @Aspect
// @Component
@Slf4j
public class LogAspect {
    //切入点表达式重用
    /*@Pointcut(value = "execution(public int com.atguigu.spring.calculator.impl.CalculatorPureImpl.add(int,int))")
    public void pointCutMethod(){}*/

    // 前置通知
    // @Before(value = "execution(public int com.atguigu.spring.calculator.impl.CalculatorPureImpl.add(int,int))")/*小试牛刀*/
    // @Before(value = "pointCutMethod()")/*切入点表达式重用*/
    // @Before(value = "com.atguigu.spring.aspect.ReferenceAspect.refCutMethod()")/*切入点表达式外部类引用*/
    /*@Before(value = "execution(* *..*PureImpl.*(..))")*//*切入点表达式语法*/
    public void BeforeMethod(JoinPoint joinPoint) {
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 编写前置通知内容
        System.out.println("[前置通知] " + methodName + "开始执行,参数是:" + Arrays.asList(args));
    }

    // 返回通知
    // @AfterReturning(value = "execution(public int com.atguigu.spring.calculator.impl.CalculatorPureImpl.add(int,int))", returning = "result")/*小试牛刀*/
    // @AfterReturning(value = "pointCutMethod()", returning = "result")/*切入点表达式重用*/
    // @AfterReturning(value = "com.atguigu.spring.aspect.ReferenceAspect.refCutMethod()", returning = "result")/*切入点表达式外部类引用*/
    /*@AfterReturning(value = "execution(* *..*PureImpl.*(..))", returning = "result")*//*切入点表达式语法*/
    public void ReturnMethodResult(JoinPoint joinPoint, int result) {
        // 获取方法名称
        String methodName = joinPoint.getSignature().getName();
        // 编写返回通知内容
        System.out.println("[返回通知] " + methodName + "的执行结果是:" + result);
    }

    // 异常通知
    // @AfterThrowing(value = "execution(public int com.atguigu.spring.calculator.impl.CalculatorPureImpl.add(int,int))",throwing = "e")/*小试牛刀*/
    // @AfterThrowing(value = "pointCutMethod()",throwing = "e")/*切入点表达式重用*/
    // @AfterThrowing(value = "com.atguigu.spring.aspect.ReferenceAspect.refCutMethod()",throwing = "e")/*切入点表达式外部类引用*/
    /*@AfterThrowing(value = "execution(* *..*PureImpl.*(..))",throwing = "e")*//*切入点表达式语法*/
    public void ThrowMethod(JoinPoint joinPoint,Exception e) {
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //编写异常通知的内容
        System.out.println("[异常通知] "+methodName+"出异常了,异常信息:"+e.getMessage());
    }

    // 后置通知
    // @After(value = "execution(public int com.atguigu.spring.calculator.impl.CalculatorPureImpl.add(int,int))")/*小试牛刀*/
    // @After(value = "pointCutMethod()")/*切入点表达式重用*/
    // @After(value = "com.atguigu.spring.aspect.ReferenceAspect.refCutMethod()")/*切入点表达式外部类引用*/
    /*@After(value = "execution(* *..*PureImpl.*(..))")*//*切入点表达式语法*/
    public void AfterMethod(JoinPoint joinPoint) {
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //编写后置通知的内容
        System.out.println("[后置通知] "+methodName+"方法执行完毕");
    }

    //环绕通知
    // @Around(value = "execution(* *..*PureImpl.*(..))")
    public Object AroundMethod(ProceedingJoinPoint joinPoint){
        //获取方法参数
        Object[] args = joinPoint.getArgs();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //方法返回值
        Object result = null;

        try {
            //前置通知
            System.out.println("[前置通知] "+methodName+"方法开始运行,参数是:"+Arrays.asList(args));
            //执行方法
            result = joinPoint.proceed(args);
            //返回通知
            System.out.println("[返回通知] "+methodName+"方法的返回值是:"+result);
        } catch (Throwable e) {
            //异常通知
            System.out.println("[异常通知] "+methodName+"出异常了,异常信息是:"+e.getMessage());
            throw new RuntimeException(e);
        } finally {
            //后置通知
            System.out.println("[后置通知] "+methodName+"方法执行完了");
        }
        return result;
    }
}
