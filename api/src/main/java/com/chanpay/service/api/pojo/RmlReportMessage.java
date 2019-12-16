package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 19:40
 * @Description:
 */

import java.util.List;

public class RmlReportMessage {
    private String sequenceId;
    private String key;
    private List<String> otherSequenceIds;
    private String objectNam = "RmlReportMessage";

    public RmlReportMessage() {
    }

    public String getObjectNam() {
        return this.objectNam;
    }

    public void setObjectNam(String objectNam) {
        this.objectNam = objectNam;
    }

    public String getSequenceId() {
        return this.sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getOtherSequenceIds() {
        return this.otherSequenceIds;
    }

    public void setOtherSequenceIds(List<String> otherSequenceIds) {
        this.otherSequenceIds = otherSequenceIds;
    }
}
