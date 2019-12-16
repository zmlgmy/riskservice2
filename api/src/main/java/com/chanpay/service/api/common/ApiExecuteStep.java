package com.chanpay.service.api.common;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-13 10:46
 * @Description:
 */

public enum ApiExecuteStep {
    total_time("总的查询时间"),
    device_query("设备指纹查询时间"),
    groovy_field("Groovy字段生成时间"),
    prepare_handle("预处理时间"),
    rule_engine("规则引擎执行时间"),
    rules_execute("具体规则执行时间"),
    functions_execute("具体函数执行时间"),
    after_handle("后续处理结果时间"),
    special_handle("context数据特殊处理时间"),
    check_params("参数校验时间"),
    field_assign("字段赋值时间"),
    velocity_query("velocity数据查询"),
    context_create("上下文构造时间"),
    cache_query("local cache查询时间");

    private String desc;

    private ApiExecuteStep(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}

