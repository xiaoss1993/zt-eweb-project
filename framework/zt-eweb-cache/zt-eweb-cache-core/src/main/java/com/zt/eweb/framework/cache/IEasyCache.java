package com.zt.eweb.framework.cache;

public interface IEasyCache {


    void put(String key, Object value);

    /**
     * @param timeout 单位：秒 s
     */
    void put(String key, Object value, long timeout);

    void remove(String key);

    <T> T get(String key);
}
