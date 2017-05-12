package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.extractor.ObjectExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/***
 * 点击元素
 */
public class ClickAction extends LuckyWebDriver implements Action {
    private static final Logger logger = LoggerFactory.getLogger(ClickAction.class);

    private ObjectExtractor extractor;

    public ObjectExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(ObjectExtractor extractor) {
        this.extractor = extractor;
    }

    public void doAction() {
        this.getExtractor().doExtractor();
        List<WebElement> elementList = this.getExtractor().getExtractResults();
        if (elementList != null && elementList.size() > 0) {
            elementList.get(0).click();
        } else {
            //由于无法抽取元素 程序无法向下进行 抛出异常
            throw new RuntimeException(this.webDriver.getCurrentUrl() + "页面处理点击事件失败");
        }
    }
}
