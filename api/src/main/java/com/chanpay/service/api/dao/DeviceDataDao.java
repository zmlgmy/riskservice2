package com.chanpay.service.api.dao;

import com.chanpay.service.api.entity.DeviceInfoVo;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DeviceDataDao{
 
  @Autowired
  private CassandraTemplate cassandraTemplate;
 
          public DeviceInfoVo save(DeviceInfoVo entity) {
    return cassandraTemplate.insert(entity);
  }
 
          public DeviceInfoVo findOne(Long id) {
    return cassandraTemplate.selectOneById(id, DeviceInfoVo.class);
  }
 
          public Iterable<DeviceInfoVo> findAll() {
    return cassandraTemplate.select("SELECT * FROM device_info ;", DeviceInfoVo.class);
  }
 
  public Iterable<DeviceInfoVo> findByCondition(DeviceInfoVo deviceDataVo) {
 
    //未建索引的列查询 须使用ALLOW FILTERING
 
    Select select = QueryBuilder.select().from("device_info").allowFiltering();
    select.where(QueryBuilder.eq("status", "1"));
    return cassandraTemplate.select(select, DeviceInfoVo.class);
  }
 
}