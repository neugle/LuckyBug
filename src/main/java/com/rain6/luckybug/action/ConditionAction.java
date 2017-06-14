package com.rain6.luckybug.action;

import com.rain6.luckybug.extractor.ObjectExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Rain6 on 2017/6/13.
 */

//条件判断分支任务
public class ConditionAction extends LuckyWebDriver implements Action {
    //是否存在元素
    private ObjectExtractor extractor;

    public ObjectExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(ObjectExtractor extractor) {
        this.extractor = extractor;
    }

    //如果存在执行任务
    private List<Action> ifActions;

    public List<Action> getIfActions() {
        return ifActions;
    }

    public void setIfActions(List<Action> ifActions) {
        this.ifActions = ifActions;
    }

    //不存在执行任务
    private List<Action> elseActions;

    public List<Action> getElseActions() {
        return elseActions;
    }

    public void setElseActions(List<Action> elseActions) {
        this.elseActions = elseActions;
    }

    public void doAction() {
        this.getExtractor().doExtractor();
        List<WebElement> elementList = this.getExtractor().getExtractResults();
        //是否存在元素
        if (elementList != null && elementList.size() > 0) {
            //执行下面一系列动作
            if (ifActions != null && ifActions.size() > 0) {
                for (Action action : ifActions) {
                    action.doAction();
                }
            }
        } else {
            //执行下面一系列动作
            if (elseActions != null && elseActions.size() > 0) {
                for (Action action : elseActions) {
                    action.doAction();
                }
            }
        }
    }
}