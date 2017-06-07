package com.rain6.luckybug.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rain6 on 2017/4/19.
 */
public class GanJISpider {

    public static void main(String[] args) throws Exception {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        webDriver.get("http://tj.ganji.com/");
        Thread.sleep(2000);

        String downloadTypeHref = webDriver.findElement(By.xpath("//a[@gjalog='100000002906000100000010@url=销售']")).getAttribute("href");
        webDriver.get(downloadTypeHref);
        Thread.sleep(2000);

        List<WebElement> dls = webDriver.findElements(By.xpath("//div[@class='new-dl-wrapper']/div/dl/dt/a"));
        List<String> hrefs = new ArrayList<String>();

        for (WebElement dl : dls) {
            hrefs.add(dl.getAttribute("href"));
            System.out.println(dl.getText());
        }

        for (String href : hrefs) {

            //获取每一页上详情
            webDriver.get(href);
            Thread.sleep(2000);

            //获取有用的所有信息
            String title = webDriver.findElement(By.xpath("//h1[@class='f24 fc4b h31']")).getText();
            String company = webDriver.findElement(By.xpath("//span[@class='firm-name']/a")).getText();
            String position = webDriver.findElement(By.xpath("//a[@class='nolink']")).getText();
            String money = webDriver.findElement(By.xpath("//em[@class='salary']")).getText();
            String phoneImg = webDriver.findElement(By.xpath("//span[@id='isShowPhoneTop']/img")).getAttribute("src");
            String phoneOwner = webDriver.findElement(By.xpath("//dl[@class='detail-contact']/dd[2]")).getText();
            phoneOwner = phoneOwner.substring(phoneOwner.indexOf("：") + 1, phoneOwner.indexOf("（") - 1).replaceAll("\n", "");
            String address = webDriver.findElement(By.xpath("//li[@class='fl w-auto']/em")).getText();
            //获取电话号码
            String phoneUrl = webDriver.findElement(By.xpath("//div[@class='d-c-left']")).getAttribute("data-pub-resume-url");
            //跳转到phone页面
            webDriver.get(phoneUrl);

            String phone = webDriver.findElement(By.xpath("//div[@class='apply-pos-v2-tit']/b")).getText();

            System.out.println(title);
            System.out.println(company);
            System.out.println(position);
            System.out.println(money);
            System.out.println(phoneImg);
            System.out.println(phoneOwner);
            System.out.println(address);
            System.out.println(phone);
        }

        Thread.sleep(2000);
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}