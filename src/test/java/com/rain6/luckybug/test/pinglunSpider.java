package com.rain6.luckybug.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by Rain6 on 2017/6/7.
 */
public class pinglunSpider {
    public static void main(String[] args) throws Exception {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("http://www.dianping.com/shop/27333487");
        Thread.sleep(2000);

        List<WebElement> elements = webDriver.findElements(By.xpath("//p[@class='user-info']/a[@class='name']"));

        for (WebElement element : elements) {
            System.out.println(element.getText());
        }

        List<WebElement> elements1 = webDriver.findElements(By.xpath("//li[@class='comment-item']/div[@class='content']"));

        for (WebElement element1 : elements1) {
            System.out.println(element1.getText());
        }

        webDriver.quit();
    }
}
