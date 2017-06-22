package com.rain6.luckybug.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Rain6 on 2017/6/13.
 */
public class ResultItems {
    //结果
    private Map<String, Object> fields = new LinkedHashMap<String, Object>();

    /***
     * 插入结果集
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void put(String key, T value) {
        fields.put(key, value);
    }

    /***
     * 根据key获取结果
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        Object o = fields.get(key);
        if (o == null) {
            return null;
        }
        return (T) o;
    }

    /***
     * 获取结果集
     * @return
     */
    public Map<String, Object> getAll() {
        return fields;
    }

    /***
     * 填充结果集
     * @param fields
     */
    public void fill(Map<String, Object> fields) {
        this.fields = fields;
    }

    public void clear() {
        fields = new HashMap<String, Object>();
    }
}
