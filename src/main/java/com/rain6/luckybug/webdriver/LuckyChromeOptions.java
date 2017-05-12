package com.rain6.luckybug.webdriver;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rain6 on 2017/5/12.
 */

/***
 * 覆写ChromeOptions类
 */
public class LuckyChromeOptions extends ChromeOptions {
    private Map<String, Object> options = new HashMap<String, Object>();

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        for (Map.Entry<String, Object> option : options.entrySet()) {
            this.setExperimentalOption(option.getKey(), option.getValue());
        }
    }
}
