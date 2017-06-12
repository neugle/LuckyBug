package com.rain6.luckybug.extractor;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rain6 on 2017/5/8.
 */

/***
 * 文本内容抽取
 */
public class TextExtractor extends StringExtractor {

    @Override
    public void afterExtractor() {
        List<String> texts = new ArrayList<String>();
        if (elements != null && elements.size() > 0) {
            for (WebElement element : elements) {
                String text = element.getText();
                if (text != null && !text.trim().equals("")) {
                    texts.add(element.getText());
                }
            }
        }
        //抽取内容
        this.setExtractResults(texts);
    }
}
