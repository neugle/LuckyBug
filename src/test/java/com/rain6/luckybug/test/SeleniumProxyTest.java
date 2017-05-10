package com.rain6.luckybug.test;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Rain6 on 2017/4/24.
 */
public class SeleniumProxyTest {
    public static void main(String[] args) {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");

        DesiredCapabilities cap = new DesiredCapabilities();
        String proxyIpAndPort = "175.45.188.6:8080";
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyIpAndPort);
        cap.setCapability(CapabilityType.PROXY, proxy);

        WebDriver webDriver = new ChromeDriver(cap);

        webDriver.get("http://tj.ganji.com/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.close();
    }
}
