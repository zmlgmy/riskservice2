package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 17:31
 * @Description:
 */
public enum PolicyMode {
    FirstMatch("首次匹配"),
    WorstMatch("最坏匹配"),
    Weighted("权重模式");

    private String displayName;

    private PolicyMode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
