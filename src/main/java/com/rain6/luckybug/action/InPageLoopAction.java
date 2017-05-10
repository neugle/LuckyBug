package com.rain6.luckybug.action;

import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.webdriver.WebDriver;

import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 详细信息页面循环
 */
public class InPageLoopAction extends WebDriver implements Action {
    private StringExtractor extractor;

    public StringExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(StringExtractor extractor) {
        this.extractor = extractor;
    }

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
            for (String element : elementList) {
                //进入详细页面
                this.webDriver.navigate().to(element);
                //进行一系列操作
                if (this.getActions() != null && this.getActions().size() > 0) {
                    for (Action action : actions) {
                        action.doAction();
                    }
                }

                //返回到之前页面
                this.webDriver.navigate().back();
            }
        } else {
            //由于无法抽取元素 程序无法向下进行 抛出异常
            throw new RuntimeException(this.webDriver.getCurrentUrl() + "页面处理跳转失败");
        }
    }
}
