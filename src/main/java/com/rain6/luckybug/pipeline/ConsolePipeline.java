package com.rain6.luckybug.pipeline;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * Created by Rain6 on 2017/4/20.
 */

/***
 * 管道输出到屏幕
 */
public class ConsolePipeline implements Pipeline {
    public void process(Map<String, Object> resultItems) {
        //序列化
        System.out.println(JSON.toJSONString(resultItems));
    }

    public void process(List<Map<String, Object>> resultItems) {
        for (Map<String, Object> resultItem : resultItems) {
            //序列化
            System.out.println(JSON.toJSONString(resultItem));
        }
    }
}
