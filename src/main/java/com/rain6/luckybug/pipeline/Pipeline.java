package com.rain6.luckybug.pipeline;

import com.rain6.luckybug.model.ResultItems;

import java.util.Map;

/**
 * Created by Rain6 on 2017/4/20.
 */

/***
 * 输出管道接口类
 */
public interface Pipeline {
    /***
     * 输出核心
     */
    void process(Map<String, Object> resultItems);
}
