package com.rain6.luckybug.action;

import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Rain6 on 2017/6/14.
 */

/***
 * 循环操作
 */
public class LoopAction extends LuckyWebDriver implements Action {
    private static final Logger logger = LoggerFactory.getLogger(LoopAction.class);


    //页面抽取元素
    private StringExtractor extractor;

    public StringExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(StringExtractor extractor) {
        this.extractor = extractor;
    }

    //循环中一系列操作
    private List<Action> actions;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void doAction() {
        this.getExtractor().doExtractor();
        List<String> elementList = this.getExtractor().getExtractResults();
        if (elementList != null && elementList.size() > 0) {

        }
    }
}
