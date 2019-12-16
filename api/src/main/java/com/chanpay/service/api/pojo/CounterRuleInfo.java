package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:44
 * @Description:
 */

public class CounterRuleInfo implements Cloneable {
    private String ruleId;
    private String timebackFlag;
    private String calcType;
    private Boolean hasRead = false;
    private int resultCount;
    private CounterRuleSumResult counterRuleSumResult;
    private int resultIndustryCount;
    private CounterRuleSumResult counterIndustryRuleSumResult;

    public CounterRuleInfo() {
    }

    public String getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getTimebackFlag() {
        return this.timebackFlag;
    }

    public void setTimebackFlag(String timebackFlag) {
        this.timebackFlag = timebackFlag;
    }

    public String getCalcType() {
        return this.calcType;
    }

    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    public Boolean getHasRead() {
        return this.hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }

    public int getResultCount() {
        return this.resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public CounterRuleSumResult getCounterRuleSumResult() {
        return this.counterRuleSumResult;
    }

    public void setCounterRuleSumResult(CounterRuleSumResult counterRuleSumResult) {
        this.counterRuleSumResult = counterRuleSumResult;
    }

    public CounterRuleInfo clone() {
        CounterRuleInfo rr = null;

        try {
            rr = (CounterRuleInfo)super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace();
        }

        return rr;
    }

    public int getResultIndustryCount() {
        return this.resultIndustryCount;
    }

    public void setResultIndustryCount(int resultIndustryCount) {
        this.resultIndustryCount = resultIndustryCount;
    }

    public CounterRuleSumResult getCounterIndustryRuleSumResult() {
        return this.counterIndustryRuleSumResult;
    }

    public void setCounterIndustryRuleSumResult(CounterRuleSumResult counterIndustryRuleSumResult) {
        this.counterIndustryRuleSumResult = counterIndustryRuleSumResult;
    }
}
