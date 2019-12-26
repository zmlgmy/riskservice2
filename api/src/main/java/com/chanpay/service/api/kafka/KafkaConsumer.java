package com.chanpay.service.api.kafka;

import com.alibaba.fastjson.JSON;
import com.chanpay.service.api.entity.ActivityMap;
import com.chanpay.service.api.entity.Item;
import com.chanpay.service.api.service.ActivityMapRepository;
import com.chanpay.service.api.service.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

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
    @Autowired
    MemcachedClient memcachedClient;

    @KafkaListener(topics = {"forseti_api_elasticsearch_message"})
    public void listenEs(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            log.info(kafkaMessage.get().toString());
            Object message = kafkaMessage.get();
            ActivityMap activityMap = JSON.parseObject(message.toString(), ActivityMap.class);
            activityMapRepository.save(activityMap);
        }

    }
    @KafkaListener(topics = {"forseti_api_memcached_message"})
    public void listenMemcached(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            try {
                memcachedClient.set("activityMap",0,kafkaMessage.get().toString());
                log.info("从memcached获取到数据：{}",memcachedClient.get("activityMap").toString());
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MemcachedException e) {
                e.printStackTrace();
            }
        }

    }
}