package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.webdriver.LuckyWebDriver;

/***
 * 打开网页
 */
public class NavigateAction extends LuckyWebDriver implements Action {

    //网页url
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public void doAction() {
        this.webDriver.navigate().to(this.getUri());
    }
}
