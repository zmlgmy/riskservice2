package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 17:22
 * @Description:
 */

import com.chanpay.service.api.common.ApiResponse;

import java.util.List;
import java.util.Map;

public class RiskResponse extends ApiResponse implements Cloneable {
    private static final long serialVersionUID = 844958112006659504L;
    private Integer final_score;
    private String final_decision;
    private String policy_name;
    private List<HitRule> hit_rules;
    private String seq_id;
    private Integer spend_time;
    private Map<String, String> geoip_info;
    private Map<String, Object> device_info;
    private List<PolicyResult> policy_set;
    private String policy_set_name;
    private String risk_type;
    private Boolean flowChargeSuccessed = false;
    private Boolean emergencySwithcOn = false;
    private String dealType;
    private String ruleDealType;
    private String ruleDealTypeName;
    private String ruleType;
    private String ruleTypeName;
    private String ruleExplanation;
    private String saas_seq_id;
    private String saas_app_name;
    private String apiServiceIp;
    private List<PolicyAction> policyActions;

    public RiskResponse() {
    }

    public String getApiServiceIp() {
        return this.apiServiceIp;
    }

    public void setApiServiceIp(String apiServiceIp) {
        this.apiServiceIp = apiServiceIp;
    }

    public String getRuleTypeName() {
        return this.ruleTypeName;
    }

    public void setRuleTypeName(String ruleTypeName) {
        this.ruleTypeName = ruleTypeName;
    }

    public String getSaas_seq_id() {
        return this.saas_seq_id;
    }

    public void setSaas_seq_id(String saas_seq_id) {
        this.saas_seq_id = saas_seq_id;
    }

    public String getSaas_app_name() {
        return this.saas_app_name;
    }

    public void setSaas_app_name(String saas_app_name) {
        this.saas_app_name = saas_app_name;
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

    public String getDealType() {
        return this.dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public static long getSerialversionuid() {
        return 844958112006659504L;
    }

    public Boolean getEmergencySwithcOn() {
        return this.emergencySwithcOn;
    }

    public void setEmergencySwithcOn(Boolean emergencySwithcOn) {
        this.emergencySwithcOn = emergencySwithcOn;
    }

    public Boolean getFlowChargeSuccessed() {
        return this.flowChargeSuccessed;
    }

    public void setFlowChargeSuccessed(Boolean flowChargeSuccessed) {
        this.flowChargeSuccessed = flowChargeSuccessed;
    }

    public Integer getFinal_score() {
        return this.final_score;
    }

    public void setFinal_score(Integer risk_score) {
        this.final_score = risk_score;
    }

    public String getFinal_decision() {
        return this.final_decision;
    }

    public void setFinal_decision(String result) {
        this.final_decision = result;
    }

    public List<HitRule> getHit_rules() {
        return this.hit_rules;
    }

    public void setHit_rules(List<HitRule> hit_rules) {
        this.hit_rules = hit_rules;
    }

    public String getSeq_id() {
        return this.seq_id;
    }

    public void setSeq_id(String seq_id) {
        this.seq_id = seq_id;
    }

    public Integer getSpend_time() {
        return this.spend_time;
    }

    public void setSpend_time(Integer spend_time) {
        this.spend_time = spend_time;
    }

    public String getPolicy_name() {
        return this.policy_name;
    }

    public void setPolicy_name(String policy_name) {
        this.policy_name = policy_name;
    }

    public Map<String, String> getGeoip_info() {
        return this.geoip_info;
    }

    public void setGeoip_info(Map<String, String> geoip_info) {
        this.geoip_info = geoip_info;
    }

    public Map<String, Object> getDevice_info() {
        return this.device_info;
    }

    public void setDevice_info(Map<String, Object> device_info) {
        this.device_info = device_info;
    }

    public List<PolicyResult> getPolicy_set() {
        return this.policy_set;
    }

    public void setPolicy_set(List<PolicyResult> policy_set) {
        this.policy_set = policy_set;
    }

    public String getPolicy_set_name() {
        return this.policy_set_name;
    }

    public void setPolicy_set_name(String policy_set_name) {
        this.policy_set_name = policy_set_name;
    }

    public String getRisk_type() {
        return this.risk_type;
    }

    public void setRisk_type(String risk_type) {
        this.risk_type = risk_type;
    }

    public List<PolicyAction> getPolicyActions() {
        return this.policyActions;
    }

    public void setPolicyActions(List<PolicyAction> policyActions) {
        this.policyActions = policyActions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("success:").append(this.success);
        sb.append(",seqId:").append(this.seq_id);
        sb.append(",result:").append(this.final_decision);
        sb.append(",reasonCode:").append(this.reason_code);
        sb.append(",costTime:").append(this.spend_time);
        return sb.toString();
    }

    public Object clone() {
        RiskResponse rr = null;

        try {
            rr = (RiskResponse)super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace();
        }

        return rr;
    }

    public void cloneFrom(RiskResponse riskResponse) {
        this.final_score = riskResponse.final_score;
        this.final_decision = riskResponse.final_decision;
        this.policy_name = riskResponse.policy_name;
        this.hit_rules = riskResponse.hit_rules;
        this.seq_id = riskResponse.seq_id;
        this.spend_time = riskResponse.spend_time;
        this.geoip_info = riskResponse.geoip_info;
        this.device_info = riskResponse.device_info;
        this.policy_set = riskResponse.policy_set;
        this.policy_set_name = riskResponse.policy_set_name;
        this.risk_type = riskResponse.risk_type;
        this.flowChargeSuccessed = riskResponse.flowChargeSuccessed;
        this.emergencySwithcOn = riskResponse.emergencySwithcOn;
        this.dealType = riskResponse.dealType;
        this.ruleDealType = riskResponse.ruleDealType;
        this.ruleDealTypeName = riskResponse.ruleDealTypeName;
        this.policyActions = riskResponse.policyActions;
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
