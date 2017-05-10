package com.rain6.luckybug.extractor;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 元素抽取
 */
public class ElementsExtractor extends ObjectExtractor {
    @Override
    public void afterExtractor() {
        if (elements != null && elements.size() > 0) {
            this.setExtractResults(elements);
        }
    }
}
