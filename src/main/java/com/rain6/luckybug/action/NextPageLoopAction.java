package com.rain6.luckybug.action;

import com.rain6.luckybug.extractor.ObjectExtractor;
import com.rain6.luckybug.webdriver.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Rain6 on 2017/5/5.
 */

/***
 * 每一页页面循环
 */
public class NextPageLoopAction extends WebDriver implements Action {
    private ObjectExtractor extractor;

    public ObjectExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(ObjectExtractor extractor) {
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
        while (true) {
            //先进行一系列操作
            if (this.getActions() != null && this.getActions().size() > 0) {
                for (Action action : actions) {
                    action.doAction();
                }
            }

            //点击下一页按钮
            this.getExtractor().doExtractor();
            List<WebElement> elementList = this.getExtractor().getExtractResults();
            if (elementList != null && elementList.size() > 0) {
                elementList.get(0).click();
            } else {
                //若没有下一页按钮则退出
                break;
            }
        }
    }
}
