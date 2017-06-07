package com.rain6.luckybug.extractor;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 抽取元素属性
 */
public class AttributeExtractor extends StringExtractor {

    private String attribute;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public void afterExtractor() {
        List<String> attrs = new ArrayList<String>();
        if (elements != null && elements.size() > 0) {
            for (WebElement element : elements) {
                attrs.add(element.getAttribute(this.getAttribute()));
            }
        }
        this.setExtractResults(attrs);
    }
}
