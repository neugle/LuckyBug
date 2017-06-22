package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.extractor.ElementsExtractor;
import com.rain6.luckybug.extractor.ObjectExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/***
 * 填充信息
 */
public class EnterTextAction extends LuckyWebDriver implements Action {
    //填充元素
    private ObjectExtractor extractor;

    public ObjectExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(ObjectExtractor extractor) {
        this.extractor = extractor;
    }

    //填充内容
    private String fillContent;

    public String getFillContent() {
        return fillContent;
    }

    public void setFillContent(String fillContent) {
        this.fillContent = fillContent;
    }

    //等待时间
    private int waitSecond = 0;

    public int getWaitSecond() {
        return waitSecond;
    }

    public void setWaitSecond(int waitSecond) {
        this.waitSecond = waitSecond;
    }

    public void doAction() {
        if (this.getExtractor() != null) {
            this.getExtractor().doExtractor();
            List<WebElement> elementList = this.getExtractor().getExtractResults();
            if (elementList != null && elementList.size() > 0) {
                try {
                    Thread.sleep(this.getWaitSecond() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebElement element = elementList.get(0);
                element.clear();
                element.sendKeys(this.getFillContent());
            }
        }
    }
}
