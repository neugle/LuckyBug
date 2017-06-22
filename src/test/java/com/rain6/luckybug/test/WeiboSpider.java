package com.rain6.luckybug.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Rain6 on 2017/5/16.
 */
public class WeiboSpider {
    public static void main(String[] args) throws Exception {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("http://weibo.com/");

        Thread.sleep(3000);

        // 向文本框中填入值
        WebElement loginname = webDriver.findElement(By.xpath("//input[@id='loginname']"));
        loginname.clear();
        loginname.sendKeys("18322342372");

        Thread.sleep(3000);

        WebElement password = webDriver.findElement(By.xpath("//input[@type='password']"));
        password.clear();
        password.sendKeys("18322342372");

        Thread.sleep(3000);

        // 点击GO按钮
        webDriver.findElement(By.xpath("//div[@class='info_list login_btn']/a")).click();
    }
}
