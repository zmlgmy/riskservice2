package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:29
 * @Description:
 */

public class MouseEvent extends Event {
    private static final long serialVersionUID = 6462811288370202048L;
    private String button;
    private int targetElementHash;
    private int pageX;
    private int pageY;
    private int clientX;
    private int clientY;

    public MouseEvent() {
    }

    public String getButton() {
        return this.button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public int getTargetElementHash() {
        return this.targetElementHash;
    }

    public void setTargetElementHash(int targetElementHash) {
        this.targetElementHash = targetElementHash;
    }

    public int getPageX() {
        return this.pageX;
    }

    public void setPageX(int pageX) {
        this.pageX = pageX;
    }

    public int getPageY() {
        return this.pageY;
    }

    public void setPageY(int pageY) {
        this.pageY = pageY;
    }

    public int getClientX() {
        return this.clientX;
    }

    public void setClientX(int clientX) {
        this.clientX = clientX;
    }

    public int getClientY() {
        return this.clientY;
    }

    public void setClientY(int clientY) {
        this.clientY = clientY;
    }
}

