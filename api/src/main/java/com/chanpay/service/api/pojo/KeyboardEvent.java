package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:31
 * @Description:
 */

public class KeyboardEvent extends Event {
    private static final long serialVersionUID = -4649491317089353679L;
    private int keycode;
    private String keytext;
    private boolean ctrl;
    private boolean alt;
    private boolean shift;
    private boolean meta;
    private int targetElementHash;
    private String value;

    public KeyboardEvent() {
    }

    public void setHotKey(int hotKey) {
        this.ctrl = (hotKey & 1) != 0;
        this.alt = (hotKey & 2) != 0;
        this.shift = (hotKey & 4) != 0;
        this.meta = (hotKey & 8) != 0;
    }

    public boolean isCtrl() {
        return this.ctrl;
    }

    public boolean isAlt() {
        return this.alt;
    }

    public boolean isShift() {
        return this.shift;
    }

    public boolean isMeta() {
        return this.meta;
    }

    public int getKeycode() {
        return this.keycode;
    }

    public void setKeycode(int keycode) {
        this.keycode = keycode;
    }

    public String getKeytext() {
        return this.keytext;
    }

    public void setKeytext(String keytext) {
        this.keytext = keytext;
    }

    public int getTargetElementHash() {
        return this.targetElementHash;
    }

    public void setTargetElementHash(int targetElementHash) {
        this.targetElementHash = targetElementHash;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
