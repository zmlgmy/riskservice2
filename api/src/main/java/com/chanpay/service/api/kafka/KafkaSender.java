package com.chanpay.service.api.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

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
    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send(Map<String, Object> map) {
        kafkaTemplate.send("forseti_api_elasticsearch_message",gson.toJson(map));
    }

}