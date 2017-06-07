package com.rain6.luckybug.action;

import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;

import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 详细信息页面循环
 */
public class InPageLoopAction extends LuckyWebDriver implements Action {
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

    //等待时间
    private int waitSecond = 0;

    public int getWaitSecond() {
        return waitSecond;
    }

    public void setWaitSecond(int waitSecond) {
        this.waitSecond = waitSecond;
    }

    //是否循环返回上层页面
    private Boolean isback = true;

    public Boolean getIsback() {
        return isback;
    }

    public void setIsback(Boolean isback) {
        this.isback = isback;
    }

    public void doAction() {
        this.getExtractor().doExtractor();
        List<String> elementList = this.getExtractor().getExtractResults();
        String currentUrl = this.webDriver.getCurrentUrl();
        if (elementList != null && elementList.size() > 0) {
            for (String element : elementList) {
                //进入详细页面
                this.webDriver.navigate().to(element);
                //执行之前是否等待
                if (this.getWaitSecond() != 0) {
                    try {
                        Thread.sleep(this.getWaitSecond() * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //进行一系列操作
                if (this.getActions() != null && this.getActions().size() > 0) {
                    for (Action action : actions) {
                        action.doAction();
                    }
                }
                //返回到之前页面
                if (this.getIsback()) {
                    this.webDriver.navigate().back();
                }
            }
            //循环结束后返回分页页面
            if (!this.getIsback()) {
                this.webDriver.navigate().to(currentUrl);
            }
        } else {
            //由于无法抽取元素 程序无法向下进行 抛出异常
            throw new RuntimeException(this.webDriver.getCurrentUrl() + "页面处理跳转失败");
        }
    }
}
