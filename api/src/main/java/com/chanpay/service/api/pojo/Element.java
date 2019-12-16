package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:32
 * @Description:
 */

import java.io.Serializable;

public class Element implements Serializable {
    private static final long serialVersionUID = -1617143652594649855L;
    private int elementHash;
    private String xpath;
    private int left;
    private int top;
    private int width;
    private int height;

    public Element() {
    }

    public int getElementHash() {
        return this.elementHash;
    }

    public void setElementHash(int elementHash) {
        this.elementHash = elementHash;
    }

    public String getXpath() {
        return this.xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public int getLeft() {
        return this.left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return this.top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
