package com.chanpay.service.api.service;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-21 10:32
 * @Description:
 */

import com.chanpay.service.api.entity.ActivityMap;
import com.chanpay.service.api.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface ActivityMapRepository extends ElasticsearchRepository<ActivityMap, String> {
}

