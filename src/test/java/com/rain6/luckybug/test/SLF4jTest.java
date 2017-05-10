package com.rain6.luckybug.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Rain6 on 2017/5/9.
 */
public class SLF4jTest {
    private static Logger logger = LoggerFactory.getLogger(SLF4jTest.class);

    public static void main(String[] args) {
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
