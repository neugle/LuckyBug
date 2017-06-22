package com.rain6.luckybug.pipeline;

import java.util.List;
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

    void process(List<Map<String, Object>> resultItems);
}
