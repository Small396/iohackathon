package com.kinger.iohackathon.operate;

import java.util.HashMap;

public class OperateCommonUtils {

    public static HashMap<String, byte[]> cacheHashMap = new HashMap<String, byte[]>();


    public static boolean set(String key, byte[] value) {
        cacheHashMap.put(key, value);
        return true;
    }

    public static byte[] get(String key) {
        return cacheHashMap.get(key);
    }

    public static byte [] del(String key) {
        byte [] value = cacheHashMap.remove(key);
        return value;
    }

    public static boolean hasKey(String key) {
        return cacheHashMap.containsKey(key);
    }
}
