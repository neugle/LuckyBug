package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.openqa.selenium.Cookie;

import java.util.List;
import java.util.Map;

/***
 * 跳转页面
 */
public class JumpAction extends LuckyWebDriver implements Action {

    private StringExtractor extractor;

    public StringExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(StringExtractor extractor) {
        this.extractor = extractor;
    }

    private Map<String, String> cookies;

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public void doAction() {
        this.getExtractor().doExtractor();
        List<String> extractResults = this.getExtractor().getExtractResults();
        if (extractResults != null && extractResults.size() > 0) {
            if (getCookies() != null && getCookies().size() > 0) {
                this.webDriver.manage().deleteAllCookies();
                for (Map.Entry<String, String> cookie : this.getCookies().entrySet())
                    this.webDriver.manage().addCookie(new Cookie(cookie.getKey(), cookie.getValue()));
            }
            this.webDriver.navigate().to(extractResults.get(0));
        } else {
            //由于无法抽取元素 程序无法向下进行 抛出异常
            throw new RuntimeException(this.webDriver.getCurrentUrl() + "页面处理页面跳转失败");
        }
    }
}
