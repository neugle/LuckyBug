package com.rain6.luckybug.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rain6 on 2017/5/11.
 */

/***
 * 手机版浏览器测试
 */
public class MobilePhoneTest {
    public static void main(String[] args) throws InterruptedException {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        Map<String, String> mobileOption = new HashMap<String, String>();
        mobileOption.put("deviceName", "Apple iPhone 6");
        options.setExperimentalOption("mobileEmulation", mobileOption);
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get("http://3g.ganji.com/tj/");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//a[@data-gjalog='100000002766000100000010@a6=zhaopin@al=201@am=%e9%94%80%e5%94%ae']")).click();
        Thread.sleep(2000);
        webDriver.findElements(By.xpath("//a[@data-gjalog='100000002328000200000010']")).get(0).click();
        Thread.sleep(2000);
        String title = webDriver.findElement(By.xpath("//h1[@class='title']")).getText();
        String phone = webDriver.findElement(By.xpath("//*[@id=\"detail_info\"]/div[8]/table/tbody/tr[1]/td")).getText();

        System.out.println(title);
        System.out.println(phone);
    }
}
