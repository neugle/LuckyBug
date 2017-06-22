package com.rain6.luckybug.extractor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Rain6 on 2017/6/13.
 */
public class ItemsExtractor extends AbstractExtractor {
    private static final Logger logger = LoggerFactory.getLogger(ItemsExtractor.class);

    private List<Map<String, Object>> extractResults;

    public List<Map<String, Object>> getExtractResults() {
        return extractResults;
    }

    public void setExtractResults(List<Map<String, Object>> extractResults) {
        this.extractResults = extractResults;
    }

    private List<Map<String, String>> xpaths;

    public List<Map<String, String>> getXpaths() {
        return xpaths;
    }

    public void setXpaths(List<Map<String, String>> xpaths) {
        this.xpaths = xpaths;
    }

    @Override
    public void afterExtractor() {
        extractResults = new ArrayList<Map<String, Object>>();
        //找到最外层元素
        if (elements != null && elements.size() > 0) {
            //获取元素
            for (WebElement element : elements) {
                //继续寻找相关元素
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                for (Map<String, String> xpath : xpaths) {
                    for (Map.Entry<String, String> kv : xpath.entrySet()) {
                        String key = kv.getKey();
                        String value = null;
                        try {
                            value = element.findElement(By.xpath("." + kv.getValue())).getText();
                        } catch (Exception ex) {
                            logger.error("抽取元素时出错,出错页面url:" + this.webDriver.getCurrentUrl() + ",等待抽取元素xpath:" + kv.getValue() + "");
                        }
                        map.put(key, value);
                    }
                }
                extractResults.add(map);
            }
        }
    }
}
