package com.rain6.luckybug;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.thread.LuckyBugThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 主程序入口
 */
public class LuckyBug {
    private static final Logger logger = LoggerFactory.getLogger(LuckyBug.class);

    //任务数
    private static List<String> tasksXmlPath = new ArrayList<String>();
    //并行线程数
    private static int nThreads = 1;

    public static void main(String[] args) {
        //设置浏览器驱动位置
        //System.getProperties().setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        System.getProperties().setProperty("webdriver.chrome.driver", "../driver/chromedriver.exe");
        logger.info("浏览器初始化完成");
        //初始化任务
        init();
        //使用线程池执行
        ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        for (String taskXmlPath : tasksXmlPath) {
            LuckyBugThread thread = new LuckyBugThread();
            thread.setXmlFilePath(taskXmlPath);
            pool.execute(thread);
        }
    }

    private static void init() {
        //tasksXmlPath.add("tasks/work/ajk_zf.xml");
        tasksXmlPath.add("../conf/ajk_zf.xml");
    }
}
