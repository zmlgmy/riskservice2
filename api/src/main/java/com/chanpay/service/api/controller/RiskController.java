package com.chanpay.service.api.controller;

import com.chanpay.service.api.kafka.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        Map<String, Object> map = new HashMap<>();
        map.put("activity",requestMap);
        kafkaSender.send(map);
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
}
