package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:33
 * @Description:
 */

import java.io.Serializable;

public class CrackInfo implements Serializable {
    private static final long serialVersionUID = -3714215574345652292L;
    private String type;
    private String caller;
    private int calleeHash;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaller() {
        return this.caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public int getCalleeHash() {
        return this.calleeHash;
    }

    public void setCalleeHash(int calleeHash) {
        this.calleeHash = calleeHash;
    }

    public CrackInfo(String type, String caller, int calleeHash) {
        this.type = type;
        this.caller = caller;
        this.calleeHash = calleeHash;
    }
}

