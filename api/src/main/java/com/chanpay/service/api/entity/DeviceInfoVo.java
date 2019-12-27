package com.chanpay.service.api.entity;

import java.io.Serializable;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table("device_info")
public class DeviceInfoVo implements Serializable{
   private static final long serialVersionUID = 3531754743416202852L;
  
   /**
     * 存储主键
     */
    @PrimaryKey
    private Long id;
  
    @Column(value = "deviceId")
    private Long deviceId;
    @Column(value = "property")
    private String property;
 
    @Column(value = "value")
    private String value;
 

    /**
     * @return the id
     */
    public Long getId() {
      return id;
    }
 
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
      this.id = id;
    }
 
    /**
     * @return the deviceId
     */
    public Long getDeviceId() {
      return deviceId;
    }
 
    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(Long deviceId) {
      this.deviceId = deviceId;
    }
    /**
     * @return the property
     */
    public String getProperty() {
      return property;
    }
 
    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
      this.property = property;
    }
 
    /**
     * @return the value
     */
    public String getValue() {
      return value;
    }
 
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
      this.value = value;
    }

    public DeviceInfoVo() {
    }

    public DeviceInfoVo(Long id, Long deviceId, String property, String value) {
        this.id = id;
        this.deviceId = deviceId;
        this.property = property;
        this.value = value;
    }
}