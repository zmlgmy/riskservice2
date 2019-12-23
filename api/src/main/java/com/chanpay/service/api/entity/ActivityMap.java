package com.chanpay.service.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-23 14:23
 * @Description:
 */
@Data
@Document(indexName = "activity",type = "docs", shards = 1, replicas = 0)
public class ActivityMap {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private Map<String, Object> activity;
}
