package com.rain6.luckybug.test;


import org.apache.log4j.Logger;

/**
 * Created by Rain6 on 2017/5/9.
 */
public class Log4jTest {
    private static Logger logger = Logger.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
