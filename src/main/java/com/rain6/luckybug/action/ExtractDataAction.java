package com.rain6.luckybug.action;

/**
 * Created by Rain6 on 2017/5/4.
 */

import com.rain6.luckybug.extractor.StringExtractor;
import com.rain6.luckybug.model.ResultItems;
import com.rain6.luckybug.pipeline.Pipeline;
import com.rain6.luckybug.webdriver.WebDriver;

import java.util.List;

/***
 * 提取数据
 */
public class ExtractDataAction extends WebDriver implements Action {

    //抽取规则
    private List<StringExtractor> extractors;

    public List<StringExtractor> getExtractors() {
        return extractors;
    }

    public void setExtractors(List<StringExtractor> extractors) {
        this.extractors = extractors;
    }

    //抽取过程执行操作
    private List<Action> actions;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
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
        //爬取当前页信息
        if (this.getExtractors() != null && this.getExtractors().size() > 0) {
            for (StringExtractor extractor : extractors) {
                extractor.doExtractor();
                List<String> results = extractor.getExtractResults();
                if (results != null && results.size() > 0) {
                    //装载数据
                    ResultItems.put(extractor.getName(), results.get(0));
                }
            }
        }

        //继续当前页面操作
        if (this.getActions() != null && this.getActions().size() > 0) {
            for (Action action : actions) {
                action.doAction();
            }
        }

        //通过管道输出
        if (this.getPipeline() != null && this.getPipeline().size() > 0) {
            for (Pipeline pipeline : this.getPipeline()) {
                if (ResultItems.getAll().size() > 0) {
                    pipeline.process(ResultItems.getAll());
                }
            }
            //清空集合
            ResultItems.clear();
        }
    }
}
