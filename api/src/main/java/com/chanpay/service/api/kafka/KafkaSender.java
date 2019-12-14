package com.chanpay.service.api.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-14 11:11
 * @Description:
 */
@Component
public class KafkaSender {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send(Map<String, String> map) {
        kafkaTemplate.send("zhisheng", gson.toJson(map));
    }
}