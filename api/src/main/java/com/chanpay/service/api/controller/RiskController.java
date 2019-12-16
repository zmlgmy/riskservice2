package com.chanpay.service.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chanpay.service.api.common.ActionCode;
import com.chanpay.service.api.common.ApiExecuteStep;
import com.chanpay.service.api.common.OneSendEvent;
import com.chanpay.service.api.kafka.KafkaSender;
import com.chanpay.service.api.pojo.FraudContext;
import com.chanpay.service.api.pojo.RiskResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-14 10:43
 * @Description:
 */
@RestController
@Slf4j
public class RiskController {
    @Autowired
    KafkaSender kafkaSender;

    @PostMapping("/riskService")
    public void getRiskInfo(HttpServletRequest request){
        Map<String, String> requestMap = this.fetchParams(request);
        long startTime = System.currentTimeMillis();
        RiskResponse response = new RiskResponse();
        FraudContext context = new FraudContext();
        context.setRequestTraceStartTime(startTime);
        response.setFinal_decision(ActionCode.Accept.name());
        context.executeTimeMap.put(ApiExecuteStep.prepare_handle.name(), System.currentTimeMillis() - startTime);
        String saasSqId;
        if (!response.getEmergencySwithcOn()) {
            context.clearVelocityData();
            context.clearVelocityRuleResult();
            context.clearIfRuleResult();
            response.setSpend_time((int) (System.currentTimeMillis() - startTime));
            context.executeTimeMap.put(ApiExecuteStep.total_time.toString(), response.getSpend_time());
            String msg = "RESP:";
            if (!response.isSuccess()) {
                msg = "RESP_F:";
            }

            log.info(msg + JSON.toJSONString(response, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect}));
            RiskResponse responseSave = (RiskResponse) response.clone();
            saasSqId = responseSave.getFinal_decision();
            if (ActionCode.Reject.name().equals(saasSqId)) {
                context.getSystemFiels().put("payStatus", "RmReject");
                context.getSystemFiels().put("payResult", "风控系统拒绝");
            }
            asyncStoreEvent(context, response);
        }
    }
    private Map<String, String> fetchParams(HttpServletRequest request) {
        Map map = request.getParameterMap();
        Map<String, String> requestMap = new HashMap(map.size());
        Iterator itr = map.entrySet().iterator();

        while(itr.hasNext()) {
            Map.Entry entry = (Map.Entry)itr.next();
            String key = (String)entry.getKey();
            String value = request.getParameter(key);
            if (StringUtils.isNotBlank(value)) {
                requestMap.put(key, value.trim());
            } else {
                requestMap.put(key, value);
            }
        }

        return requestMap;
    }
    private void asyncStoreEvent(FraudContext context, RiskResponse response) {
        if (context != null && response != null && response.isSuccess()) {
            int model = 3;
            Object payStatus = context.get("payStatus");
            String eventId = context.getEventId();
            if (!OneSendEvent.binding.name().equals(eventId) && !OneSendEvent.Authentication.name().equals(eventId) && !OneSendEvent.modify.name().equals(eventId) && !OneSendEvent.login.name().equals(eventId) && !OneSendEvent.register.name().equals(eventId)) {
                if (payStatus == null || StringUtils.isBlank((String)payStatus)) {
                    model = 1;
                    Object key = context.get("tradeSrcNo");
                    if (key == null || StringUtils.isBlank((String)key)) {
                        key = context.get("traSeq");
                    }

                    if (key == null || StringUtils.isBlank((String)key)) {
                        model = 3;
                    }

                    Object batchNumber = context.get("batchNumber");
                    if (batchNumber != null) {
                        model = 3;
                    }
                }
            } else {
                model = 3;
            }

            log.info("model:{}", Integer.valueOf(model));
        }
        kafkaSender.onProduce(context, response);
    }
}
