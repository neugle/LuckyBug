package com.rain6.luckybug.action;

import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 详细信息页面循环
 */
public class InPageLoopAction extends LuckyWebDriver implements Action {
    private static final Logger logger = LoggerFactory.getLogger(InPageLoopAction.class);

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
                try {
                    //进入详细页面
                    logger.info("InPageLoopAction Info:from " + this.webDriver.getCurrentUrl() + " to " + element);
                    this.webDriver.navigate().to(element);
                } catch (Exception ex) {
                    //防止超时
                    logger.warn(element + "页面加载超时");
                }
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
                try {
                    logger.info("InPageLoopAction Info:from " + this.webDriver.getCurrentUrl() + " to " + currentUrl);
                    this.webDriver.navigate().to(currentUrl);
                } catch (Exception ex) {
                    //防止超时
                    logger.warn(currentUrl + "页面加载超时");
                }
            }
        } else {
            //由于无法抽取元素 程序无法向下进行 抛出异常
            //throw new RuntimeException("InPageLoopAction Error:");
            logger.warn("InPageLoopAction Error:" + this.webDriver.getCurrentUrl() + "页面抽取不到相关元素");
        }
    }
}
