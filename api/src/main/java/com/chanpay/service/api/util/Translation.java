package com.chanpay.service.api.util;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-16 14:51
 * @Description:
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


@Slf4j
public class Translation {

    public Translation() {
    }

    public static Map<String, Object> translateFromCassandra(Map<String, Object> json) {
        json = cloneMap(json);
        Long now = parseLong(JsonPath.getJsonValue(json, "timestamp"));
        translateJson(json, "policy.policySet");
        translateJson(json, "policy.ruleDetail");
        translateTime(json, "activity.eventOccurTime");
        translateTime(json, "activity.accountBirthday");
        JsonPath.setJsonValue(json, "event_result.policyList", getJsonValueList(json, "policy.policySet", "policy_name"));
        JsonPath.setJsonValue(json, "event_result.ruleUuids", getJsonValueList(json, "policy.policySet", "hit_rules", "uuid"));
        translateComma(json, "event_result.ruleList");
        defaultJsonValue(json, "activity.eventOccurTime", now);
        defaultJsonValue(json, "event_result.timestamp", now);
        translateComma(json, "event_result.riskType");
        Object riskType = JsonPath.getJsonValue(json, "event_result.riskType");
        JsonPath.setJsonValue(json, "event_result.riskTypeAll", getRiskTypeAll((List)riskType));
        JsonPath.setJsonValue(json, "event_result.riskTypeReject", getRiskTypeReject((List)riskType));
        JsonPath.setJsonValue(json, "event_result.riskTypeReview", getRiskTypeReview((List)riskType));
        json.put("policyString2", extractPolicyString(new JSONObject(json)));
        json.put("policy", extractPolicyMap(new JSONObject(json)));
        json.remove("timestamp");
        json = normalizeMap(json);
        return json;
    }

    private static Map<String, Object> toStringStringMap(Map<String, Object> deviceMap) {
        if (deviceMap == null) {
            return null;
        } else {
            Map<String, Object> result = new HashMap();
            Iterator var2 = deviceMap.keySet().iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                Object value = deviceMap.get(key);
                if (value != null) {
                    if (value instanceof Map) {
                        value = JSONObject.toJSONString(value);
                    }

                    result.put(key, value.toString());
                }
            }

            return result;
        }
    }

    private static String extractPolicyString(JSONObject json) {
        JSONObject policyJson = json.getJSONObject("policy");
        if (policyJson == null) {
            return null;
        } else {
            return policyJson.isEmpty() ? null : policyJson.toJSONString();
        }
    }

    private static JSONObject extractPolicyMap(JSONObject json) {
        JSONObject policyJson = json.getJSONObject("policy");
        if (policyJson == null) {
            return null;
        } else {
            JSONArray policySetList = policyJson.getJSONArray("policySet");
            if (policySetList == null) {
                return null;
            } else {
                List<JSONObject> hitRules = new ArrayList();
                Iterator var4 = policySetList.iterator();

                while(true) {
                    JSONObject policySetJson;
                    JSONArray hitRulesOnePolicy;
                    do {
                        Object policySet;
                        do {
                            if (!var4.hasNext()) {
                                JSONObject result = new JSONObject();
                                result.put("policySet", policySetList);
                                result.put("hitRules", hitRules);
                                return result;
                            }

                            policySet = var4.next();
                        } while(policySet == null);

                        policySetJson = (JSONObject)policySet;
                        hitRulesOnePolicy = policySetJson.getJSONArray("hit_rules");
                    } while(hitRulesOnePolicy == null);

                    Iterator var8 = hitRulesOnePolicy.iterator();

                    while(var8.hasNext()) {
                        Object hitRule = var8.next();
                        JSONObject hitRuleJson = (JSONObject)hitRule;
                        hitRuleJson.put("policyUuid", policySetJson.get("policy_uuid"));
                    }

                    //hitRules.addAll(hitRulesOnePolicy);
                    policySetJson.remove("hit_rules");
                }
            }
        }
    }

    private static Object normalize(Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof Map) {
            return normalizeMap((Map)object);
        } else if (object instanceof List) {
            return normalizeList((List)object);
        } else if (object instanceof Date) {
            return ((Date)object).getTime();
        } else if (object instanceof String) {
            return normalizeString((String)object);
        } else if (object instanceof Long) {
            return object;
        } else if (object instanceof Integer) {
            return object;
        } else if (object instanceof Float) {
            return object;
        } else if (object instanceof Double) {
            return object;
        } else {
            return object instanceof Boolean ? object : object.toString();
        }
    }

    private static String normalizeString(String s) {
        return s.equals("") ? null : s;
    }

    private static Map<String, Object> normalizeMap(Map<String, Object> json) {
        Map<String, Object> result = new HashMap();
        Iterator var2 = json.keySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            Object value = json.get(key);
            value = normalize(value);
            if (value != null) {
                result.put(key, value);
            }
        }

        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }
    }

    private static List<Object> normalizeList(List<Object> list) {
        List<Object> result = new LinkedList();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            Object e = var2.next();
            e = normalize(e);
            result.add(e);
        }

        return list.isEmpty() ? null : result;
    }

    private static List<String> getRiskTypeAll(List<String> riskType) {
        return getRiskType(riskType, EnumSet.of(Translation.RiskType.ACCEPT, Translation.RiskType.REJECT, Translation.RiskType.REVIEW, Translation.RiskType.OTHER));
    }

    private static List<String> getRiskTypeReview(List<String> riskType) {
        return getRiskType(riskType, EnumSet.of(Translation.RiskType.REVIEW));
    }

    private static List<String> getRiskTypeReject(List<String> riskType) {
        return getRiskType(riskType, EnumSet.of(Translation.RiskType.REJECT, Translation.RiskType.OTHER));
    }

    private static List<String> getRiskType(List<String> riskType, EnumSet<Translation.RiskType> types) {
        if (riskType == null) {
            return null;
        } else {
            List<String> result = new LinkedList();
            Iterator var3 = riskType.iterator();

            while(true) {
                while(var3.hasNext()) {
                    String s = (String)var3.next();
                    Translation.RiskType[] var5 = Translation.RiskType.values();
                    int var6 = var5.length;

                    for(int var7 = 0; var7 < var6; ++var7) {
                        Translation.RiskType rt = var5[var7];
                        if (s.endsWith(rt.getPostfix())) {
                            if (types.contains(rt)) {
                                result.add(removePostfix(s, rt.getPostfix()));
                            }
                            break;
                        }
                    }
                }

                if (result.isEmpty()) {
                    return null;
                }

                return result;
            }
        }
    }

    public static String getRiskTypeRemovePostfix(String riskType, EnumSet<Translation.RiskType> types) {
        Translation.RiskType[] var2 = Translation.RiskType.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Translation.RiskType rt = var2[var4];
            if (riskType.endsWith(rt.getPostfix())) {
                if (types.contains(rt)) {
                    return removePostfix(riskType, rt.getPostfix());
                }
                break;
            }
        }

        return riskType;
    }

    public static String removePostfix(String s, String postfix) {
        return s.endsWith(postfix) ? StringUtils.substring(s, 0, s.length() - postfix.length()) : s;
    }

    private static Long parseLong(Object o) {
        if (o == null) {
            return null;
        } else {
            try {
                return Long.parseLong(o.toString());
            } catch (Exception var2) {
                log.error("Translation error", var2);
                return null;
            }
        }
    }

    private static void defaultJsonValue(Map<String, Object> json, String key, Object value) {
        if (JsonPath.getJsonValue(json, key) == null) {
            JsonPath.setJsonValue(json, key, value);
        }

    }

    private static <T> T cloneMapOrList(T o) {
        if (o instanceof Map) {
            return (T) cloneMap((Map)o);
        } else {
            return o instanceof List ? (T) cloneList((List)o) : o;
        }
    }

    public static Map<String, Object> cloneMap(Map<String, Object> json) {
        Map<String, Object> result = new HashMap();
        Iterator var2 = json.keySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            Object value = json.get(key);
            result.put(key, cloneMapOrList(value));
        }

        return result;
    }

    private static List<Object> cloneList(List<Object> list) {
        List<Object> result = new LinkedList();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            Object o = var2.next();
            result.add(cloneMapOrList(o));
        }

        return result;
    }

    private static void translateJson(Map<String, Object> json, String key) {
        Object value = JsonPath.getJsonValue(json, key);
        if (value != null) {
            if (value instanceof String) {
                JsonPath.setJsonValue(json, key, parseJson(value));
            }
        }
    }

    private static void translateComma(Map<String, Object> json, String key) {
        Object value = JsonPath.getJsonValue(json, key);
        if (value != null) {
            if (value instanceof String) {
                JsonPath.setJsonValue(json, key, commaToList(value.toString()));
            }
        }
    }

    private static List<Object> commaToList(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        } else {
            List<Object> list = new LinkedList();
            String[] var2 = value.split(",");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String v = var2[var4];
                list.add(v);
            }

            return list;
        }
    }

    public static List<Object> getJsonValueList(Map<String, Object> json, String... keys) {
        Object value;
        if (keys.length == 1) {
            value = JsonPath.getJsonValue(json, keys[0]);
            return value == null ? null : Collections.singletonList(value);
        } else {
            value = JsonPath.getJsonValue(json, keys[0]);
            if (!(value instanceof List)) {
                return null;
            } else {
                List<Object> ja = (List)value;
                List<Object> result = new LinkedList();
                Iterator var5 = ja.iterator();

                while(var5.hasNext()) {
                    Object o = var5.next();

                    try {
                        List<Object> list = getJsonValueList((Map)o, (String[])subarray(keys, 1, String.class));
                        if (list != null) {
                            result.addAll(list);
                        }
                    } catch (Exception var8) {
                        log.error("Translation error", var8);
                    }
                }

                return result.isEmpty() ? null : result;
            }
        }
    }

    private static <T> T[] subarray(T[] x, int start, Class<T> t) {
        T[] result = (T[]) Array.newInstance(t, x.length - start);
        System.arraycopy(x, start, result, 0, result.length);
        return result;
    }

    private static void translateTime(Map<String, Object> json, String key) {
        Object value = JsonPath.getJsonValue(json, key);
        if (value != null) {
            JsonPath.setJsonValue(json, key, parseTime(value));
        }
    }

    private static void translateBoolean(Map<String, Object> json, String key) {
        Object value = JsonPath.getJsonValue(json, key);
        if (value != null) {
            if (value instanceof String) {
                JsonPath.setJsonValue(json, key, parseBoolean(value));
            }
        }
    }

    private static void translateInteger(Map<String, Object> json, String key) {
        Object value = JsonPath.getJsonValue(json, key);
        if (value != null) {
            if (value instanceof String) {
                JsonPath.setJsonValue(json, key, parseInteger(value));
            }
        }
    }

    private static void translateLong(Map<String, Object> json, String key) {
        Object value = JsonPath.getJsonValue(json, key);
        if (value != null) {
            if (value instanceof String) {
                JsonPath.setJsonValue(json, key, parseLong(value));
            }
        }
    }

    private static void translateDouble(Map<String, Object> json, String key) {
        Object value = JsonPath.getJsonValue(json, key);
        if (value != null) {
            if (value instanceof String) {
                JsonPath.setJsonValue(json, key, parseDouble(value));
            }
        }
    }

    private static Object parseJson(Object s) {
        try {
            if (s instanceof Map) {
                return new JSONObject((Map)s);
            } else {
                return s instanceof List ? new JSONArray((List)s) : JSON.parse(s.toString());
            }
        } catch (Exception var2) {
            log.error("failed to parse json: " + s, var2);
            return null;
        }
    }

    private static Long parseTime(Object s) {
        if (s instanceof Date) {
            return ((Date)s).getTime();
        } else {
            try {
                return DateUtil.parseDateTime(s.toString()).getTime();
            } catch (ParseException var2) {
                log.error("failed to parse time: " + s, var2);
                return null;
            }
        }
    }

    private static Boolean parseBoolean(Object s) {
        if (s == null) {
            return null;
        } else {
            String x = s.toString();
            if (x.equals("是")) {
                return true;
            } else if (x.equals("否")) {
                return false;
            } else if (x.equalsIgnoreCase("true")) {
                return true;
            } else if (x.equalsIgnoreCase("false")) {
                return false;
            } else {
                log.error("failed to parse boolean: " + s);
                return null;
            }
        }
    }

    private static Integer parseInteger(Object s) {
        try {
            return Integer.parseInt(s.toString());
        } catch (Exception var2) {
            log.error("failed to parse integer: " + s, var2);
            return null;
        }
    }

    private static Double parseDouble(Object s) {
        try {
            return Double.parseDouble(s.toString());
        } catch (Exception var2) {
            log.error("failed to parse double: " + s, var2);
            return null;
        }
    }

    public static enum RiskType {
        ACCEPT("_accept"),
        REVIEW("_review"),
        REJECT("_reject"),
        OTHER("");

        private String postfix;

        private RiskType(String postfix) {
            this.postfix = postfix;
        }

        public String getPostfix() {
            return this.postfix;
        }
    }
}
