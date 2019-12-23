package com.chanpay.service.api.kafka;

import com.alibaba.fastjson.JSON;
import com.chanpay.service.api.entity.ActivityMap;
import com.chanpay.service.api.entity.Item;
import com.chanpay.service.api.service.ActivityMapRepository;
import com.chanpay.service.api.service.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-14 11:12
 * @Description:
 */
@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private ActivityMapRepository activityMapRepository;

    @KafkaListener(topics = {"forseti_api_elasticsearch_message"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();
            ActivityMap activityMap = JSON.parseObject(message.toString(), ActivityMap.class);
            activityMapRepository.save(activityMap);
            Iterable<ActivityMap> all = activityMapRepository.findAll();
            for (ActivityMap item:all){
                System.out.println(item);
            }
        }

    }
}