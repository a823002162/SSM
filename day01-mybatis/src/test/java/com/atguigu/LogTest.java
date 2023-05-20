package com.atguigu;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: Gavin
 * @Date: 5/11/2023 4:30 PM
 */
public class LogTest {
    //先创建日志对象
    private Logger logger = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void test1(){
        logger.trace("I'm trace");
        logger.debug("I'm debug");
        logger.info("I'm info");
        logger.warn("I'm warn");
        logger.error("I'm error");
    }
}
