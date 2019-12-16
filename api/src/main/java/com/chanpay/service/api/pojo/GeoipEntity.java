package com.chanpay.service.api.pojo;

/**
* @Author: liweiei
* @CreateTime: 2019-12-12 19:36
* @Description:
*/

import java.io.Serializable;

public class GeoipEntity implements Serializable {
    private static final long serialVersionUID = -753878787878321L;
    private long lip = 0L;
    private String ip = "";
    private String country = "";
    private String countryId = "";
    private String province = "";
    private String provinceId = "";
    private String city = "";
    private String cityId = "";
    private String county = "";
    private String countyId = "";
    private String area = "";
    private String areaId = "";
    private String address = "";
    private float latitude;
    private float longitude;
    private String extra1 = "";
    private String extra2 = "";
    private String isp = "";
    private String ispId = "";
    private String type = "";
    private String desc = "";

    public GeoipEntity() {
    }

    public String getIp() {
    return this.ip;
    }

    public void setIp(String ip) {
    this.ip = ip;
    }

    public long getLip() {
    return this.lip;
    }

    public void setLip(long lip) {
    this.lip = lip;
    }

    public String getCountry() {
    return this.country;
    }

    public void setCountry(String country) {
    this.country = country;
    }

    public String getCountryId() {
    return this.countryId;
    }

    public void setCountryId(String countryId) {
    this.countryId = countryId;
    }

    public String getArea() {
    return this.area;
    }

    public void setArea(String area) {
    this.area = area;
    }

    public String getAreaId() {
    return this.areaId;
    }

    public void setAreaId(String areaId) {
    this.areaId = areaId;
    }

    public String getProvince() {
    return this.province;
    }

    public void setProvince(String region) {
    this.province = region;
    }

    public String getProvinceId() {
    return this.provinceId;
    }

    public void setProvinceId(String regionId) {
    this.provinceId = regionId;
    }

    public String getCity() {
    return this.city;
    }

    public void setCity(String city) {
    this.city = city;
    }

    public String getCityId() {
    return this.cityId;
    }

    public void setCityId(String cityId) {
    this.cityId = cityId;
    }

    public String getCounty() {
    return this.county;
    }

    public void setCounty(String county) {
    this.county = county;
    }

    public String getCountyId() {
    return this.countyId;
    }

    public void setCountyId(String countyId) {
    this.countyId = countyId;
    }

    public String getIsp() {
    return this.isp;
    }

    public void setIsp(String isp) {
    this.isp = isp;
    }

    public String getIspId() {
    return this.ispId;
    }

    public void setIspId(String ispId) {
    this.ispId = ispId;
    }

    public String getAddress() {
    return this.address;
    }

    public void setAddress(String address) {
    this.address = address;
    }

    public String getExtra1() {
    return this.extra1;
    }

    public void setExtra1(String extra1) {
    this.extra1 = extra1;
    }

    public String getExtra2() {
    return this.extra2;
    }

    public void setExtra2(String extra2) {
    this.extra2 = extra2;
    }

    public float getLatitude() {
    return this.latitude;
    }

    public void setLatitude(float latitude) {
    this.latitude = latitude;
    }

    public float getLongitude() {
    return this.longitude;
    }

    public void setLongitude(float longitude) {
    this.longitude = longitude;
    }

    public String getType() {
    return this.type;
    }

    public void setType(String type) {
    this.type = type;
    }

    public String getDesc() {
    return this.desc;
    }

    public void setDesc(String desc) {
    this.desc = desc;
    }

    public String toString() {
    return "IpInfoEntity [lip=" + this.lip + ", ip=" + this.ip + ", country=" + this.country + ", countryId=" + this.countryId + ", area=" + this.area + ", areaId=" + this.areaId + ", region=" + this.province + ", regionId=" + this.provinceId + ", city=" + this.city + ", cityId=" + this.cityId + ", county=" + this.county + ", countyId=" + this.countyId + ", isp=" + this.isp + ", ispId=" + this.ispId + ", address=" + this.address + ", lat=" + this.latitude + ", lon=" + this.longitude + "]";
    }
}
