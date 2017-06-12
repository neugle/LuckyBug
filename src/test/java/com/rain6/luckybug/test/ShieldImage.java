package com.rain6.luckybug.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rain6 on 2017/6/9.
 */

/***
 * 屏蔽图片
 */
public class ShieldImage {
    public static void main(String[] args) {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        Map<String, Object> contentSettings = new HashMap<String, Object>();
        contentSettings.put("images", 2);

        Map<String, Object> preferences = new HashMap<String, Object>();
        preferences.put("profile.default_content_setting_values", contentSettings);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);
        WebDriver driver = new ChromeDriver(options);
        //WebDriver driver = new ChromeDriver();
        driver.get("https://tj.zu.anjuke.com/fangyuan/1070138055?from=Filter_1&hfilter=filterlist");

    }
}
