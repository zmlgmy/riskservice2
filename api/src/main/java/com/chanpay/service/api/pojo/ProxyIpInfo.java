package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-16 15:01
 * @Description:
 */

import java.sql.Timestamp;

/**
 * 一条“代理IP数据”表示类。
 *
 * @author huagang.li 2015年03月30日 4:57 PM
 */
public class ProxyIpInfo {

    private String    ip;          // 代理IP/待检测IP

    private int       isProxy;     // 是否是“代理IP”标识（1：是，0：否）

    private Timestamp lastScantime;

    private String    port;        // 代理端口

    private String    proxyType;   // 代理类型

    public int getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(int isProxy) {
        this.isProxy = isProxy;
    }

    public Timestamp getLastScantime() {
        return lastScantime;
    }

    public void setLastScantime(Timestamp lastScantime) {
        this.lastScantime = lastScantime;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    @Override
    public String toString() {
        return "ProxyIpInfo{" +
                "ip='" + ip + '\'' +
                ", isProxy=" + isProxy +
                ", lastScantime=" + lastScantime +
                ", port='" + port + '\'' +
                ", proxyType='" + proxyType + '\'' +
                '}';
    }

}
