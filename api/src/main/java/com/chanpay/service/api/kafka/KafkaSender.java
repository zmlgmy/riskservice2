package com.chanpay.service.api.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chanpay.service.api.pojo.*;
import com.chanpay.service.api.util.CommonTools;
import com.chanpay.service.api.util.DataUtil;
import com.chanpay.service.api.util.Translation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-14 11:11
 * @Description:
 */
@Component
@Slf4j
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final Set<String> NO_FIELD = Collections.unmodifiableSet(new HashSet<String>(2) {
        private static final long serialVersionUID = 1L;

        {
            this.add("ruleDealType");
            this.add("ruleDealTypeName");
            this.add("dealType");
        }
    });
    private static final Pattern pattern = Pattern.compile("(\\\\ud[8-9a-b][0-9a-f]{2})|(\\\\ud[c-f][0-9a-f]{2})", 2);
    private Gson gson = new GsonBuilder().create();
    private Producer<String, String> producer;
    private Random random = new SecureRandom();

    //发送消息方法
    public void send(Map<String, String> map) {
        kafkaTemplate.send("zhisheng", gson.toJson(map));
    }
    public void onProduce(FraudContext ctx, RiskResponse resp) {
        String esStr = this.parseEsStr(ctx, resp);
        if (StringUtils.isNotEmpty(esStr)) {
            log.debug("[原事后]TOPIC:forseti_api_elasticsearch_message 开始写入kafka:" + esStr);
            produce("elasticsearch_message", esStr);
            log.info("[原事后]TOPIC:forseti_api_elasticsearch_message 写入kafka已执行");
        }

    }
    public void produce(String topic, String message) {
        String prefix = "forseti_api_";
        String key = Long.toString(this.random.nextLong());
        KeyedMessage<String, String> keyedMessage = new KeyedMessage(prefix + topic, key, message);
        this.producer.send(keyedMessage);
    }
    public String parseEsStr(FraudContext ctx, RiskResponse resp) {
        List<String> encryptedFields = null;
        Map<String, String> encodeFields = CommonTools.encryptedFields(ctx, (List)encryptedFields);
        Map<String, Object> merge = new HashMap();
        merge.put("activity", this.replaceFieldsInMap(this.encodeActivity(ctx, resp), encodeFields));
        merge.put("event_result", this.replaceFieldsInMap(this.encodeEventResult(ctx, resp), encodeFields));
        merge.put("browser", this.encodeBrowser(ctx, resp));
        merge.put("geo", this.encodeGeo(ctx, resp));
        merge.put("policy", this.encodePolicy(ctx, resp));
        merge.put("device", this.encodeDevice(ctx, resp));
        merge.put("audit", this.encodeAudit(ctx, resp));
        merge.put("timestamp", System.currentTimeMillis());
        merge = Translation.translateFromCassandra(merge);
        return !merge.isEmpty() ? JSON.toJSONString(merge, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect}) : null;
    }
    private JSONObject replaceFieldsInMap(JSONObject source, Map<String, String> target) {
        if (null == source) {
            return null;
        } else {
            if (null != target && !target.isEmpty()) {
                Iterator var3 = target.keySet().iterator();

                while(var3.hasNext()) {
                    String key = (String)var3.next();
                    if (source.containsKey(key)) {
                        source.put(key, target.get(key));
                    }
                }
            }

            return source;
        }
    }
    private JSONObject encodeBrowser(FraudContext context, RiskResponse response) {
        Map<String, Object> result = new HashMap();
        this.setBrowserMap(context, result, response);
        result = this.stripEmptyFields(result);
        return new JSONObject(result);
    }
    private JSONObject encodeGeo(FraudContext context, RiskResponse response) {
        Map<String, Object> result = new HashMap();
        this.setGeoMap(context, response, result);
        result = this.stripEmptyFields(result);
        return new JSONObject(result);
    }
    private void setGeoMap(FraudContext ctx, RiskResponse resp, Map<String, Object> geoMap) {
        geoMap.put("sequenceId", resp.getSeq_id());
        if (ctx.getDeviceInfo() != null && ctx.getDeviceInfo().get("trueIp") != null) {
            geoMap.put("trueIpAddress", String.valueOf(ctx.getDeviceInfo().get("trueIp")));
        }

        if (ctx.getGeoipEntity() != null) {
            geoMap.put("ipAddress", StringUtils.defaultString(ctx.getGeoipEntity().getIp()));
            geoMap.put("ipCountry", StringUtils.defaultString(ctx.getGeoipEntity().getCountry()));
            geoMap.put("ipCity", StringUtils.defaultString(ctx.getGeoipEntity().getCity()));
            geoMap.put("ipIsp", StringUtils.defaultString(ctx.getGeoipEntity().getIsp()));
            if ((double)ctx.getGeoipEntity().getLongitude() == 0.0D && (double)ctx.getGeoipEntity().getLatitude() == 0.0D) {
                geoMap.put("ipLongitudeLatitude", "");
            } else {
                String ipLongitude = (double)ctx.getGeoipEntity().getLongitude() == 0.0D ? "" : Float.toString(ctx.getGeoipEntity().getLongitude());
                String ipLatitude = (double)ctx.getGeoipEntity().getLatitude() == 0.0D ? "" : Float.toString(ctx.getGeoipEntity().getLatitude());
                String ipLongitudeLatitude = ipLongitude + " / " + ipLatitude;
                geoMap.put("ipLongitudeLatitude", ipLongitudeLatitude);
            }

            geoMap.put("ipProvince", StringUtils.defaultString(ctx.getGeoipEntity().getProvince()));
            geoMap.put("ipCounty", StringUtils.defaultString(ctx.getGeoipEntity().getCounty()));
            geoMap.put("IpStreet", StringUtils.defaultString(ctx.getGeoipEntity().getAddress()));
        }

    }

    private JSONObject encodePolicy(FraudContext context, RiskResponse response) {
        Map<String, Object> result = new HashMap();
        this.setPolicyMap(context, response, result);
        result = this.stripEmptyFields(result);
        return new JSONObject(result);
    }
    private void setPolicyMap(FraudContext ctx, RiskResponse resp, Map<String, Object> policyMap) {
        List<PolicyResult> policySet = resp.getPolicy_set();
        if (policySet != null && policySet.size() > 0) {
            policyMap.put("policySet", JSON.toJSONString(policySet));
        }

    }
    private JSONObject encodeDevice(FraudContext context, RiskResponse response) {
        Map<String, Object> result = new HashMap();
        if (context.getDeviceInfo() != null) {
            result.putAll(this.getStrMap(context.getDeviceInfo(), response));
        }

        result = this.stripEmptyFields(result);
        return new JSONObject(result);
    }
    public Map<String, Object> getStrMap(Map<String, Object> map, RiskResponse resp) {
        Map<String, Object> strMap = new HashMap();
        strMap.put("sequenceId", resp.getSeq_id());
        Iterator var4 = map.keySet().iterator();

        while(var4.hasNext()) {
            String key = (String)var4.next();
            Object obj = map.get(key);
            if (obj == null) {
                strMap.put(key, "");
            }

            if (obj instanceof Date) {
                strMap.put(key, ((Date)obj).getTime());
            } else if (obj instanceof Boolean) {
                strMap.put(key, (Boolean)obj ? "是" : "否");
            } else {
                strMap.put(key, obj);
            }
        }

        return strMap;
    }
    private Map<String, Object> encodeAudit(FraudContext context, RiskResponse response) {
        return null;
    }
    private JSONObject encodeActivity(FraudContext context, RiskResponse response) {
        Map<String, Object> result = new HashMap();
        List<RuleFieldDO> ruleFieldDOs = new ArrayList<>();
        this.setActivityMap(context, response, result, ruleFieldDOs);
        result = this.stripEmptyFields(result);
        if (response.getSeq_id().endsWith("SAAS")) {
            context.setAppName(context.getAppName() + "SAAS");
        }

        return new JSONObject(result);
    }
    private void setActivityMap(FraudContext ctx, RiskResponse resp, Map<String, Object> activityMap, List<RuleFieldDO> ruleFieldDOs) {
        activityMap.put("sequenceId", resp.getSeq_id());
        Iterator iterator = ruleFieldDOs.iterator();

        while(true) {
            RuleFieldDO ruleFieldDO;
            do {
                if (!iterator.hasNext()) {
                    if (StringUtils.isNotBlank(ctx.getEventType())) {
                        activityMap.put("eventType", ctx.getEventType());
                    }

                    if (StringUtils.isNotBlank(ctx.getEventId())) {
                        activityMap.put("eventId", ctx.getEventId());
                    }

                    return;
                }

                ruleFieldDO = (RuleFieldDO)iterator.next();
            } while(resp.getSeq_id().endsWith("SAAS") && NO_FIELD.contains(ruleFieldDO.getName()));

            Object fieldValue = ctx.get(ruleFieldDO.getName());
            if (fieldValue != null && !"".equals(fieldValue)) {
                if ("items".equals(ruleFieldDO.getName()) && fieldValue instanceof String && StringUtils.isNotBlank((String)fieldValue)) {
                    Matcher matcher = pattern.matcher((String)fieldValue);
                    fieldValue = matcher.replaceAll("");

                    try {
                        fieldValue = DataUtil.decodeUnicode((String)fieldValue);
                    } catch (Exception var10) {
                        log.warn("unicode decode error：" + var10.getMessage(), var10);
                    }
                }

                activityMap.put(ruleFieldDO.getName(), fieldValue);
            }
        }
    }
    private JSONObject encodeEventResult(FraudContext context, RiskResponse response) {
        Map<String, Object> result = new HashMap();
        this.setEventResultMap(context, response, result);
        result = this.stripEmptyFields(result);
        return new JSONObject(result);
    }
    private Map<String, Object> stripEmptyFields(Map<String, Object> map) {
        Map<String, Object> result = new HashMap();
        Iterator var3 = map.keySet().iterator();

        while(true) {
            String key;
            Object value;
            do {
                do {
                    if (!var3.hasNext()) {
                        return result;
                    }

                    key = (String)var3.next();
                    value = map.get(key);
                } while(value == null);
            } while(value instanceof String && value.equals(""));

            result.put(key, value);
        }
    }
    private void setEventResultMap(FraudContext ctx, RiskResponse resp, Map<String, Object> eventResultMap) {
        String partnerCode = ctx.getPartnerCode();
        String appName = ctx.getAppName();
        String eventId = ctx.getEventId();
        eventResultMap.put("sequenceId", resp.getSeq_id());
        eventResultMap.put("policySetName", resp.getPolicy_name());
        eventResultMap.put("dealType", resp.getDealType());
        eventResultMap.put("riskType", resp.getRisk_type());
        eventResultMap.put("riskStatus", resp.getFinal_decision());
        eventResultMap.put("deviceId", ctx.getDeviceId());
        eventResultMap.put("ipAddress", ctx.getIpAddress());
        eventResultMap.put("accountLogin", ctx.getAccountLogin());
        eventResultMap.put("eventId", ctx.getEventId());
        eventResultMap.put("partnerCode", partnerCode);
        eventResultMap.put("appName", appName);
        eventResultMap.put("appType", ctx.getAppType());
        eventResultMap.put("riskScore", resp.getFinal_score());
        if (ctx.getGeoipEntity() != null) {
            String ipCityStr = ctx.getGeoipEntity().getCity();
            String ipProviceStr = ctx.getGeoipEntity().getProvince();
            if (StringUtils.isNotBlank(ipCityStr)) {
                eventResultMap.put("ipCity", ipCityStr);
            } else if (StringUtils.isNotBlank(ipProviceStr)) {
                eventResultMap.put("ipCity", ipProviceStr);
            } else {
                eventResultMap.put("ipCity", "");
            }
        }

       /* if (this.localCache.getPartnerInfo() != null) {
            eventResultMap.put("eventType", this.localCache.getPartnerInfo().getEventTypeMap().get(partnerCode + "." + appName + "." + eventId));
            eventResultMap.put("partnerDisName", this.localCache.getPartnerInfo().getPartnerDisplayMap().get(partnerCode));
            eventResultMap.put("appDisName", this.localCache.getPartnerInfo().getAppDisplayMap().get(partnerCode + "," + appName));
        }*/

        List<HitRule> HitRuleList = resp.getHit_rules();
        StringBuilder hitRuleNameString = new StringBuilder();
        if (HitRuleList != null) {
            Iterator var9 = HitRuleList.iterator();

            while(var9.hasNext()) {
                HitRule hitRule = (HitRule)var9.next();
                hitRuleNameString.append(hitRule.getName() + ",");
            }
        }

        eventResultMap.put("ruleList", hitRuleNameString.length() > 0 ? hitRuleNameString.toString().substring(0, hitRuleNameString.length() - 1) : "");
    }
    private void setBrowserMap(FraudContext ctx, Map<String, Object> browserMap, RiskResponse resp) {
        browserMap.put("sequenceId", resp.getSeq_id());
        if (ctx.getDeviceInfo() != null) {
            if (ctx.getDeviceInfo().get("enabledCk") != null) {
                browserMap.put("enabledCookie", this.toBoolean(ctx.getDeviceInfo().get("enabledCk")));
            }

            if (ctx.getDeviceInfo().get("enabledFl") != null) {
                browserMap.put("enabledFlash", this.toBoolean(ctx.getDeviceInfo().get("enabledFl")));
            }

            if (ctx.getDeviceInfo().get("enabledJs") != null) {
                browserMap.put("enabledJs", this.toBoolean(ctx.getDeviceInfo().get("enabledJs")));
            }

            if (ctx.getDeviceInfo().get("imageLoaded") != null) {
                browserMap.put("enabledImage", this.toBoolean(ctx.getDeviceInfo().get("imageLoaded")));
            }

            String referer = (String)ctx.getDeviceInfo().get("refer");
            String userAgent = (String)ctx.getDeviceInfo().get("userAgent");
            if (referer != null) {
                browserMap.put("referer", referer);
            }

            if (userAgent != null) {
                browserMap.put("userAgent", userAgent);
            }

            if (ctx.getDeviceInfo().get("timeZone") != null) {
                browserMap.put("timeZone", String.valueOf(ctx.getDeviceInfo().get("timeZone")));
            }

            if (ctx.getDeviceInfo().get("browser") != null) {
                browserMap.put("browserName", String.valueOf(ctx.getDeviceInfo().get("browser")));
            }

            if (ctx.getDeviceInfo().get("browserVersion") != null) {
                browserMap.put("browserVersion", String.valueOf(ctx.getDeviceInfo().get("browserVersion")));
            }

            String langReString;
            String[] langReStringArr;
            if (ctx.getDeviceInfo().get("screenRes") != null) {
                langReString = (String)ctx.getDeviceInfo().get("screenRes");
                if (langReString != null) {
                    langReStringArr = langReString.split("\\^\\^");
                    if (langReStringArr.length == 4) {
                        browserMap.put("screenRes", langReStringArr[0] + "*" + langReStringArr[1]);
                        browserMap.put("screenDpi", langReStringArr[2]);
                        browserMap.put("screenColor", langReStringArr[3]);
                    }
                }
            }

            if (ctx.getDeviceInfo().get("langRes") != null) {
                langReString = (String)ctx.getDeviceInfo().get("langRes");
                if (langReString != null) {
                    langReStringArr = langReString.split("\\^\\^");
                    if (langReStringArr.length == 5) {
                        browserMap.put("browserLanguage", langReStringArr[0]);
                        browserMap.put("systemLanguage", langReStringArr[3]);
                        browserMap.put("userLanguage", langReStringArr[4]);
                    }
                }
            }
        }

    }
    private String toBoolean(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Boolean) {
            return (Boolean)o ? "是" : "否";
        } else if (o instanceof String) {
            String s = (String)o;
            if (s.equalsIgnoreCase("true")) {
                return "是";
            } else {
                return s.equalsIgnoreCase("false") ? "否" : s;
            }
        } else {
            return o.toString();
        }
    }
}