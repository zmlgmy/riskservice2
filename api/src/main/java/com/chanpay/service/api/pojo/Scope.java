package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:42
 * @Description:
 */

public enum Scope {
    APPLICATION("本应用"),
    PARTNER("合作方"),
    GLOBAL("全局"),
    GLOBALEXCEPTPARTNER("全局(本合作方除外)");

    private String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    private Scope(String displayName) {
        this.displayName = displayName;
    }
}
