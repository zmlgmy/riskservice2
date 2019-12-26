package com.chanpay.service.api.controller;

import com.chanpay.service.api.dao.DeviceDataDao;
import com.chanpay.service.api.entity.DeviceInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-26 17:50
 * @Description:
 */
@RestController
@Slf4j
public class CassandraController {
    @Autowired
    private DeviceDataDao deviceDataDao;
    @PostMapping("/save")
    public void save(){
        DeviceInfoVo deviceDataVo = new DeviceInfoVo(1212L,1212L,"color","white");
        deviceDataDao.save(deviceDataVo);
    }
    @PostMapping("/findAll")
    public List<DeviceInfoVo> findAll(){
        return (List<DeviceInfoVo>) deviceDataDao.findAll();
    }
}
