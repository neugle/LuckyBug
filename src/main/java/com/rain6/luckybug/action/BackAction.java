package com.rain6.luckybug.action;

import com.rain6.luckybug.webdriver.LuckyWebDriver;

/**
 * Created by Rain6 on 2017/5/9.
 */

/***
 * 返回上一页
 */
public class BackAction extends LuckyWebDriver implements Action {
    public void doAction() {
        this.webDriver.navigate().back();
    }
}
