package com.rain6.luckybug.action;

import com.rain6.luckybug.extractor.ObjectExtractor;
import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 每一页页面循环
 */
public class NextPageLoopAction extends LuckyWebDriver implements Action {
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

    public void doAction() {
        while (true) {
            //执行之前是否等待
            if (this.getWaitSecond() != 0) {
                try {
                    Thread.sleep(this.getWaitSecond() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //先进行一系列操作
            if (this.getActions() != null && this.getActions().size() > 0) {
                for (Action action : actions) {
                    action.doAction();
                }
            }

            //跳转到下一页
            this.getExtractor().doExtractor();
            List<String> elementList = this.getExtractor().getExtractResults();
            if (elementList != null && elementList.size() > 0) {
                this.webDriver.navigate().to(elementList.get(0));
            } else {
                //若没有下一页按钮则退出
                break;
            }
        }
    }
}
