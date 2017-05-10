package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.webdriver.WebDriver;

import java.util.List;

/***
 * 跳转页面
 */
public class JumpAction extends WebDriver implements Action {

    private StringExtractor extractor;

    public StringExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(StringExtractor extractor) {
        this.extractor = extractor;
    }

    public void doAction() {
        this.getExtractor().doExtractor();
        List<String> extractResults = this.getExtractor().getExtractResults();
        if (extractResults != null && extractResults.size() > 0) {
            this.webDriver.navigate().to(extractResults.get(0));
        } else {
            //由于无法抽取元素 程序无法向下进行 抛出异常
            throw new RuntimeException(this.webDriver.getCurrentUrl() + "页面处理页面跳转失败");
        }
    }
}
