package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 17:26
 * @Description:
 */

public class RuleAction {
    private String id;
    private String uuid;
    private String name;
    private int score = 0;
    private String action;
    private long costTime;
    private String parentUuid;
    private int testRun = 0;
    private String dealType;
    private String dealTypeName;
    private String dealTypeGrade;

    public RuleAction() {
    }

    public int getTestRun() {
        return this.testRun;
    }

    public void setTestRun(int testRun) {
        this.testRun = testRun;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getCostTime() {
        return this.costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentUuid() {
        return this.parentUuid;
    }

    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid;
    }

    public String getDealType() {
        return this.dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealTypeName() {
        return this.dealTypeName;
    }

    public void setDealTypeName(String dealTypeName) {
        this.dealTypeName = dealTypeName;
    }

    public String getDealTypeGrade() {
        return this.dealTypeGrade;
    }

    public void setDealTypeGrade(String dealTypeGrade) {
        this.dealTypeGrade = dealTypeGrade;
    }
}
