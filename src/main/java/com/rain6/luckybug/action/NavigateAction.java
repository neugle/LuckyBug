package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 打开网页
 */
public class NavigateAction extends LuckyWebDriver implements Action {
    private static final Logger logger = LoggerFactory.getLogger(NavigateAction.class);
    //网页url
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public void doAction() {
        try {
            logger.info("NavigateAction Info:from " + this.webDriver.getCurrentUrl() + " to " + this.getUri() + "");
            this.webDriver.navigate().to(this.getUri());
        } catch (Exception ex) {
            throw new RuntimeException("网络原因 请重新运行程序");
        }
    }
}
