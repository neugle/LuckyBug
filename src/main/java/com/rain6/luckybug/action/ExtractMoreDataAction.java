package com.rain6.luckybug.action;

import com.rain6.luckybug.extractor.ItemsExtractor;
import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.model.ResultItems;
import com.rain6.luckybug.pipeline.Pipeline;
import com.rain6.luckybug.webdriver.LuckyWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Rain6 on 2017/6/13.
 */
public class ExtractMoreDataAction extends LuckyWebDriver implements Action {
    private static final Logger logger = LoggerFactory.getLogger(ExtractMoreDataAction.class);
    //抽取规则
    private ItemsExtractor itemsExtractor;

    public ItemsExtractor getItemsExtractor() {
        return itemsExtractor;
    }

    public void setItemsExtractor(ItemsExtractor itemsExtractor) {
        this.itemsExtractor = itemsExtractor;
    }

    //抽取唯一标识
    private StringExtractor stringExtractor;

    public StringExtractor getStringExtractor() {
        return stringExtractor;
    }

    public void setStringExtractor(StringExtractor stringExtractor) {
        this.stringExtractor = stringExtractor;
    }

    //输出管道集合
    private List<Pipeline> pipeline;

    public List<Pipeline> getPipeline() {
        return pipeline;
    }

    public void setPipeline(List<Pipeline> pipeline) {
        this.pipeline = pipeline;
    }

    public void doAction() {
        try {
            String pk = null;
            if (this.getStringExtractor() != null) {
                stringExtractor.doExtractor();
                pk = stringExtractor.getExtractResults().get(0);
            }

            //爬取当前页信息
            if (this.getItemsExtractor() != null) {
                itemsExtractor.doExtractor();
                List<Map<String, Object>> results = itemsExtractor.getExtractResults();
                List<Map<String, Object>> resultItems = new ArrayList<Map<String, Object>>();
                for (Map<String, Object> result : results) {
                    ResultItems resultItem = new ResultItems();
                    //装载数据
                    resultItem.fill(result);

                    resultItem.put("pk", pk);
                    //记录当前时间
                    resultItem.put("cdate", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

                    resultItems.add(resultItem.getAll());
                }

                //通过管道输出
                if (this.getPipeline() != null && this.getPipeline().size() > 0) {
                    for (Pipeline pipeline : this.getPipeline()) {
                        if (resultItems.size() > 0) {
                            pipeline.process(resultItems);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}
