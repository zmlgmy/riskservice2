package com.chanpay.service.api.service;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-21 10:32
 * @Description:
 */

import com.chanpay.service.api.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    List<Item> findByPriceBetween(double price1, double price2);
}

