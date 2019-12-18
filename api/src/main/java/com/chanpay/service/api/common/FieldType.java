package com.chanpay.service.api.common;

public enum FieldType {
    INT("整数"),
    DOUBLE("小数"),
    STRING("字符串"),
    BOOLEAN("布尔类型"),
    ARRAY("数组类型"),
    DATETIME("日期类型（格式：2014-03-01 08:16:30）");

    private String displayName;

    private FieldType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
