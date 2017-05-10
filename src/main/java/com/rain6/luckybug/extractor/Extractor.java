package com.rain6.luckybug.extractor;

/**
 * Created by Rain6 on 2017/5/5.
 */
public interface Extractor {
    boolean beforeExtractor();

    void doExtractor();

    void innerExtractor();

    void afterExtractor();
}
