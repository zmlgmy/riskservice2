package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 16:39
 * @Description:
 */

import java.io.Serializable;

public class HitRule implements Serializable {
    private static final long serialVersionUID = 6297666052880082771L;
    private String id;
    private String uuid;
    private String name;
    private String decision;
    private Integer score = 0;
    private String dealType;
    private String dealTypeName;
    private String dealTypeGrade;
    private String ruleType;
    private String ruleExplanation;
    private String parentUuid;

    public HitRule() {
    }

    public String getRuleType() {
        return this.ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleExplanation() {
        return this.ruleExplanation;
    }

    public void setRuleExplanation(String ruleExplanation) {
        this.ruleExplanation = ruleExplanation;
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

    public String getDealType() {
        return this.dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecision() {
        return this.decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getParentUuid() {
        return this.parentUuid;
    }

    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid;
    }
}

