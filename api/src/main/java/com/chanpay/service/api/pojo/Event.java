package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:30
 * @Description:
 */

import java.io.Serializable;

public abstract class Event implements Comparable<Event>, Serializable {
    private static final long serialVersionUID = 2145055580905628479L;
    private long eventTime;
    private String eventType;
    private int eventNumber;

    public Event() {
    }

    public long getEventTime() {
        return this.eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventNumber() {
        return this.eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public int compareTo(Event o) {
        return this.eventNumber - o.eventNumber;
    }
}

