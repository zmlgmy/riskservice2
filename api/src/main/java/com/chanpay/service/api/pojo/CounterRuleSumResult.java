package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:43
 * @Description:
 */

public class CounterRuleSumResult {
    private double preSum;
    private double curValue;

    public CounterRuleSumResult() {
    }

    public double getPreSum() {
        return this.preSum;
    }

    public void setPreSum(double preSum) {
        this.preSum = preSum;
    }

    public double getCurValue() {
        return this.curValue;
    }

    public void setCurValue(double curValue) {
        this.curValue = curValue;
    }
}

