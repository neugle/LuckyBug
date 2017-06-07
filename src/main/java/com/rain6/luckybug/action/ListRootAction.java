package com.rain6.luckybug.action;

import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Rain6 on 2017/6/6.
 */
public class ListRootAction extends LuckyWebDriver implements Action {
    private static final Logger logger = LoggerFactory.getLogger(ListRootAction.class);

    //初始种子
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    //初始种子action
    private NavigateAction seedAction;

    public NavigateAction getSeedAction() {
        return seedAction;
    }

    public void setSeedAction(NavigateAction seedAction) {
        this.seedAction = seedAction;
    }

    //操作集合
    private List<Action> actions;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void doAction() {
        try {
            if (getUrls() != null && getUrls().size() > 0) {
                for (String url : getUrls()) {
                    if (this.seedAction != null) {
                        this.seedAction.setUri(url);
                        this.seedAction.doAction();
                    }
                    if (getActions() != null && getActions().size() > 0) {
                        for (Action action : getActions()) {
                            action.doAction();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
