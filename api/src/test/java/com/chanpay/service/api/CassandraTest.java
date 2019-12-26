package com.chanpay.service.api;

import com.chanpay.service.api.entity.DeviceInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-24 10:39
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class CassandraTest {
    @Autowired
    private CassandraTemplate cassandraTemplate;

    @Test
    public void save() {

        DeviceInfoVo deviceDataVo = new DeviceInfoVo(1212L,1212L,"color","white");
        cassandraTemplate.insert(deviceDataVo);
    }
}
