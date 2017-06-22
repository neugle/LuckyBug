package com.rain6.luckybug.thread;

import com.rain6.luckybug.action.Action;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Rain6 on 2017/5/9.
 */

/***
 * 执行线程
 */
public class LuckyBugThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(LuckyBugThread.class);

    //xml文件路径
    private String xmlFilePath;

    public String getXmlFilePath() {
        return xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public void run() {
        logger.info("任务开始");
        FileSystemXmlApplicationContext apx = new FileSystemXmlApplicationContext(this.getXmlFilePath());
        BeanFactory factory = (BeanFactory) apx;
        Action action = (Action) factory.getBean("rootAction");
        action.doAction();
        //再次获取当前浏览器实例 并且关闭
        WebDriver webDriver = (WebDriver) factory.getBean("webDriver");
        //webDriver.quit();
        logger.info("任务结束");
    }
}
