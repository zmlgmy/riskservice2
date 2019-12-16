package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 17:26
 * @Description:
 */

import java.util.ArrayList;
import java.util.List;

public class PolicyAction {
    private String policyUuid;
    private String policyName;
    private String policySetName;
    private String finalAction;
    private String riskType;
    private int riskScore;
    private long costTime;
    private PolicyMode policyMode;
    private int priority;
    private List<RuleAction> actionList = new ArrayList();
    private String dealType;
    private String dealTypeName;
    private String dealTypeGrade;

    public PolicyAction() {
    }

    public String getFinalAction() {
        return this.finalAction;
    }

    public void setFinalAction(String finalAction) {
        this.finalAction = finalAction;
    }

    public int getRiskScore() {
        return this.riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public List<RuleAction> getActionList() {
        return this.actionList;
    }

    public void setActionList(List<RuleAction> actionList) {
        this.actionList = actionList;
    }

    public long getCostTime() {
        return this.costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getPolicyUuid() {
        return this.policyUuid;
    }

    public void setPolicyUuid(String policyUuid) {
        this.policyUuid = policyUuid;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicySetName() {
        return this.policySetName;
    }

    public void setPolicySetName(String policySetName) {
        this.policySetName = policySetName;
    }

    public String getRiskType() {
        return this.riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public PolicyMode getPolicyMode() {
        return this.policyMode;
    }

    public void setPolicyMode(PolicyMode policyMode) {
        this.policyMode = policyMode;
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
