package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 17:34
 * @Description:
 */


import java.io.Serializable;
import java.util.List;

public class PolicyResult implements Serializable {
    private static final long serialVersionUID = -965304098400137796L;
    private String policy_uuid;
    private String policy_name;
    private String policy_decision;
    private int policy_score;
    private String risk_type;
    private List<HitRule> hit_rules;
    private List<HitRule> hit_test_rules;
    private PolicyMode policy_mode;
    private String dealType;
    private String ruleDealType;
    private String ruleDealTypeName;

    public PolicyResult() {
    }

    public String getDealType() {
        return this.dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getPolicy_uuid() {
        return this.policy_uuid;
    }

    public void setPolicy_uuid(String policy_uuid) {
        this.policy_uuid = policy_uuid;
    }

    public String getPolicy_name() {
        return this.policy_name;
    }

    public void setPolicy_name(String policy_name) {
        this.policy_name = policy_name;
    }

    public String getPolicy_decision() {
        return this.policy_decision;
    }

    public void setPolicy_decision(String policy_decision) {
        this.policy_decision = policy_decision;
    }

    public int getPolicy_score() {
        return this.policy_score;
    }

    public void setPolicy_score(int policy_score) {
        this.policy_score = policy_score;
    }

    public String getRisk_type() {
        return this.risk_type;
    }

    public void setRisk_type(String risk_type) {
        this.risk_type = risk_type;
    }

    public List<HitRule> getHit_rules() {
        return this.hit_rules;
    }

    public void setHit_rules(List<HitRule> hit_rules) {
        this.hit_rules = hit_rules;
    }

    public PolicyMode getPolicy_mode() {
        return this.policy_mode;
    }

    public void setPolicy_mode(PolicyMode policy_mode) {
        this.policy_mode = policy_mode;
    }

    public List<HitRule> getHit_test_rules() {
        return this.hit_test_rules;
    }

    public void setHit_test_rules(List<HitRule> hit_test_rules) {
        this.hit_test_rules = hit_test_rules;
    }

    public String getRuleDealType() {
        return this.ruleDealType;
    }

    public void setRuleDealType(String ruleDealType) {
        this.ruleDealType = ruleDealType;
    }

    public String getRuleDealTypeName() {
        return this.ruleDealTypeName;
    }

    public void setRuleDealTypeName(String ruleDealTypeName) {
        this.ruleDealTypeName = ruleDealTypeName;
    }
}
