package com.rain6.luckybug.webdriver;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Rain6 on 2017/5/4.
 */

/***
 * 获取浏览器本身抽象
 */
public abstract class WebDriver {
    @Autowired
    protected org.openqa.selenium.WebDriver webDriver;
}
