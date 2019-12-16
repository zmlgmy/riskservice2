package com.chanpay.service.api.common;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-13 10:43
 * @Description:
 */

public enum ActionCode {
    Accept("通过"),
    Reject("拒绝"),
    Review("人工审核");

    private String displayName;

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    private ActionCode(String displayName) {
        this.displayName = displayName;
    }

    public ActionCode getByName(String name) {
        if (name != null) {
            ActionCode[] codes = values();
            ActionCode[] var3 = codes;
            int var4 = codes.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                ActionCode code = var3[var5];
                if (code.name().equals(name)) {
                    return code;
                }
            }
        }

        return null;
    }

    public static String getDisplayName(String code) {
        try {
            return valueOf(code).getDisplayName();
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }
}

