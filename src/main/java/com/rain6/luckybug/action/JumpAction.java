package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/***
 * 跳转页面
 */
public class JumpAction extends LuckyWebDriver implements Action {
    private static final Logger logger = LoggerFactory.getLogger(JumpAction.class);

    private StringExtractor extractor;

    public StringExtractor getExtractor() {
        return extractor;
    }

    public void setExtractor(StringExtractor extractor) {
        this.extractor = extractor;
    }

    /*private Map<String, String> cookies;

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }*/

    public void doAction() {
        this.getExtractor().doExtractor();
        List<String> extractResults = this.getExtractor().getExtractResults();
        //记录跳转前位置
        String initUrl = this.webDriver.getCurrentUrl();
        if (extractResults != null && extractResults.size() > 0) {
            /*if (getCookies() != null && getCookies().size() > 0) {
                this.webDriver.manage().deleteAllCookies();
                for (Map.Entry<String, String> cookie : this.getCookies().entrySet())
                    this.webDriver.manage().addCookie(new Cookie(cookie.getKey(), cookie.getValue()));
            }*/
            try {
                this.webDriver.navigate().to(extractResults.get(0));
            } catch (Exception ex) {
                //防止超时
                logger.warn(extractResults.get(0) + "页面加载超时");
            }
        } else {
            //由于无法抽取元素 程序无法向下进行 抛出异常
            //throw new RuntimeException("JumpAction Error:从" + initUrl + "页面跳转至" + extractResults.get(0) + "页面时失败");
            logger.warn("JumpAction Error:" + this.webDriver.getCurrentUrl() + "页面抽取不到相关元素");
        }
    }
}
