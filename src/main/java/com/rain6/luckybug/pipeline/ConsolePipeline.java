package com.rain6.luckybug.pipeline;

import com.rain6.luckybug.model.ResultItems;

import java.util.Map;

/**
 * Created by Rain6 on 2017/4/20.
 */

/***
 * 管道输出到屏幕
 */
public class ConsolePipeline implements Pipeline {
    public void process(Map<String, Object> resultItems) {
        for (Map.Entry<String, Object> entry : resultItems.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
}
