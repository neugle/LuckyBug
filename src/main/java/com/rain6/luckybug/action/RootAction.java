package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.webdriver.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/***
 * 流程入口
 */
public class RootAction extends WebDriver implements Action {
    private static final Logger logger = LoggerFactory.getLogger(RootAction.class);

    private List<Action> actions;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void doAction() {
        try {
            if (this.getActions() != null && this.getActions().size() > 0) {
                for (Action action : actions) {
                    action.doAction();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
