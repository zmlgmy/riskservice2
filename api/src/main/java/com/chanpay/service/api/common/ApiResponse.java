package com.chanpay.service.api.common;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 17:21
 * @Description:
 */

import java.io.Serializable;

public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 4152462611121573434L;
    protected Boolean success = false;
    protected String reason_code;
    protected Object attribute;

    public ApiResponse() {
    }

    public Object getAttribute() {
        return this.attribute;
    }

    public void setAttribute(Object attribute) {
        this.attribute = attribute;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason_code() {
        return this.reason_code;
    }

    public void setReason_code(String reason_code) {
        this.reason_code = reason_code;
    }
}

