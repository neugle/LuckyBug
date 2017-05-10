package com.rain6.luckybug.extractor;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 元素抽取抽象类
 */
public abstract class ObjectExtractor extends AbstractExtractor {
    private List<WebElement> extractResults;

    public List<WebElement> getExtractResults() {
        return extractResults;
    }

    public void setExtractResults(List<WebElement> extractResults) {
        this.extractResults = extractResults;
    }
}
