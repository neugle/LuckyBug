package com.rain6.luckybug.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rain6 on 2017/4/20.
 */
public class ResultItems {
    //结果
    private static Map<String, Object> fields = new HashMap<String, Object>();

    /***
     * 插入结果集
     * @param key
     * @param value
     * @param <T>
     */
    public static <T> void put(String key, T value) {
        fields.put(key, value);
    }

    /***
     * 根据key获取结果
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T get(String key) {
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
    public static Map<String, Object> getAll() {
        return fields;
    }

    public static void clear() {
        fields = new HashMap<String, Object>();
    }
}
