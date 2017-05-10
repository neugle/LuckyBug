package com.rain6.luckybug.extractor;

import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 字符串抽取抽象类
 */
public abstract class StringExtractor extends AbstractExtractor {
    private List<String> extractResults;

    public List<String> getExtractResults() {
        return extractResults;
    }

    public void setExtractResults(List<String> extractResults) {
        this.extractResults = extractResults;
    }


    //抽取名
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
