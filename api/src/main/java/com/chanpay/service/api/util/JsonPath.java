package com.chanpay.service.api.util;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-16 14:52
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonPath {
    public JsonPath() {
    }

    public static Object getJsonValue(Object json, String key) {
        String[] keys = key.split("\\.");

        for(int j = 0; j < keys.length; ++j) {
            if (json == null) {
                return null;
            }

            String k = keys[j];
            if (!(json instanceof Map)) {
                return null;
            }

            json = ((Map)json).get(k);
        }

        return json;
    }

    public static void setJsonValue(Map<String, Object> json, String key, Object value) {
        String[] keys = key.split("\\.");

        for(int i = 0; i < keys.length - 1; ++i) {
            String k = keys[i];
            if (!json.containsKey(k)) {
                json.put(k, new HashMap());
            }

            json = (Map)json.get(k);
        }

        String lastKey = keys[keys.length - 1];
        if (value == null) {
            json.remove(lastKey);
        } else {
            json.put(lastKey, value);
        }

    }

    public static JSONObject parsePath(Map<String, Object> list) {
        JSONObject result = new JSONObject(true);

        String key;
        Object o;
        for(Iterator var2 = list.keySet().iterator(); var2.hasNext(); setJsonValue(result, key, o)) {
            key = (String)var2.next();
            o = list.get(key);
            if (o instanceof Map) {
                o = parsePath((Map)o);
            }
        }

        return result;
    }
}
