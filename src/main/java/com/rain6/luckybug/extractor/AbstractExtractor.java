package com.rain6.luckybug.extractor;

import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 抽取抽象类
 */
public abstract class AbstractExtractor extends LuckyWebDriver implements Extractor {
    private static final Logger logger = LoggerFactory.getLogger(AbstractExtractor.class);
    //抽取元素表达式
    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    //等待时间
    private int waitSecond = 0;

    public int getWaitSecond() {
        return waitSecond;
    }

    public void setWaitSecond(int waitSecond) {
        this.waitSecond = waitSecond;
    }

    //等待页面加载时间
    private int waitTimeOutSecond = 0;

    public int getWaitTimeOutSecond() {
        return waitTimeOutSecond;
    }

    public void setWaitTimeOutSecond(int waitTimeOutSecond) {
        this.waitTimeOutSecond = waitTimeOutSecond;
    }

    //抽取元素集合
    protected List<WebElement> elements = null;

    //等待页面加载出的元素表达式
    private String waitUntilAppearExpression;

    public String getWaitUntilAppearExpression() {
        return waitUntilAppearExpression;
    }

    public void setWaitUntilAppearExpression(String waitUntilAppearExpression) {
        this.waitUntilAppearExpression = waitUntilAppearExpression;
    }

    public boolean beforeExtractor() {
        //等待页面加载
        try {
            if (this.getWaitUntilAppearExpression() != null && this.getWaitTimeOutSecond() != 0) {
                new WebDriverWait(webDriver, this.getWaitTimeOutSecond())
                        .until(ExpectedConditions.elementToBeClickable(By.xpath(this.getWaitUntilAppearExpression())));
            }
            return true;
        } catch (Exception ex) {
            logger.error("等待加载时出错,出错页面url:" + this.webDriver.getCurrentUrl() + ",等待加载元素xpath:" + this.getWaitUntilAppearExpression() + "");
            return false;
        }
    }

    public void doExtractor() {
        //执行之前是否等待
        if (this.getWaitSecond() != 0) {
            try {
                Thread.sleep(this.getWaitSecond() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //执行
        if (this.beforeExtractor()) {
            this.innerExtractor();
            this.afterExtractor();
        }
    }

    //抽取过程
    public void innerExtractor() {
        try {
            if (this.getExpression() != null) {
                elements = this.webDriver.findElements(By.xpath(this.getExpression()));
            }
        } catch (Exception ex) {
            logger.error("抽取元素时出错,出错页面url:" + this.webDriver.getCurrentUrl() + ",等待抽取元素xpath:" + this.getExpression() + "");
        }
    }

    //抽取之后整理数据
    public void afterExtractor() {

    }
}
