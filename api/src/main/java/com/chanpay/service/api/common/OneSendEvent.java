package com.chanpay.service.api.common;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-13 11:11
 * @Description:
 */

public enum OneSendEvent {
    binding("绑卡事件"),
    Authentication("鉴权事件"),
    modify("修改事件"),
    login("登录事件"),
    register("注册时间 ");

    private String description;

    private OneSendEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
