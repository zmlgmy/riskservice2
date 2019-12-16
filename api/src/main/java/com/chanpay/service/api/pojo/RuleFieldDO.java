package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-13 13:51
 * @Description:
 */

import java.io.Serializable;

/**
 * @author kai.zhang 2014年3月1日 上午11:56:15
 */
public class RuleFieldDO extends CommonDO implements Serializable, Comparable<RuleFieldDO> {

    private static final long serialVersionUID     = 8963907847949013871L;

    /**
     * 0 表示系统字段，1表示扩展字段。
     */
    private Integer           sign;
    private String            partnerCode;
    private String            appName;
    private String            appType;                                    // 系统字段适用的应用类型
    private String            name;
    private String            displayName;
    private String            createdBy;
    private String            modifiedBy;
    private String            type;
    private Integer           maxLength;
    private String            description;
    private Integer           necessary;
    private String            eventType;                                  // 事件类型
    private String            signForRule;                                // 标示是否在规则配置左变量中展示
    private boolean           signForVelocity;                            // 标示是否作为主维度过滤
    private boolean           signForSAAS;                            // 标示是否发送SAAS的标志
    private String            eventTypeDisplayName = "";
    private String            typeDisplayName      = "";
    private String            eventId;
    private String            appDisplayName;
    private String            partnerDisplayName;
    private String            appTypeName;                                // 系统字段适用的应用类型展示名
    private String            fieldValue;
    private String            saasName;



    public String getSaasName() {
        return saasName;
    }

    public void setSaasName(String saasName) {
        this.saasName = saasName;
    }

    public boolean isSignForSAAS() {
        return signForSAAS;
    }

    public void setSignForSAAS(boolean signForSAAS) {
        this.signForSAAS = signForSAAS;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public RuleFieldDO(){
        super();
    }

    public RuleFieldDO(String name, String displayName){
        super();
        this.name = name;
        this.displayName = displayName;
    }

    public String getAppTypeName() {
        return appTypeName;
    }

    public void setAppTypeName(String appTypeName) {
        this.appTypeName = appTypeName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getTypeDisplayName() {
        return typeDisplayName;
    }

    public void setTypeDisplayName(String typeDisplayName) {
        this.typeDisplayName = typeDisplayName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public boolean isSignForVelocity() {
        return signForVelocity;
    }

    public void setSignForVelocity(boolean signForVelocity) {
        this.signForVelocity = signForVelocity;
    }

    public String getEventTypeDisplayName() {
        return eventTypeDisplayName;
    }

    public void setEventTypeDisplayName(String eventTypeDisplayName) {
        this.eventTypeDisplayName = eventTypeDisplayName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDisplayName() {
        return appDisplayName;
    }

    public void setAppDisplayName(String appDisplayName) {
        this.appDisplayName = appDisplayName;
    }

    public String getPartnerDisplayName() {
        return partnerDisplayName;
    }

    public void setPartnerDisplayName(String partnerDisplayName) {
        this.partnerDisplayName = partnerDisplayName;
    }

    public String getSignForRule() {
        return signForRule;
    }

    public void setSignForRule(String signForRule) {
        this.signForRule = signForRule;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNecessary() {
        return necessary;
    }

    public void setNecessary(Integer necessary) {
        this.necessary = necessary;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.displayName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RuleFieldDO other = (RuleFieldDO) obj;
        if (eventType == null) {
            if (other.eventType != null) return false;
        } else if (!eventType.equals(other.eventType)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }

    @Override
    public int compareTo(RuleFieldDO o) {
        return this.getDisplayName().compareTo(o.getDisplayName());
    }

    public boolean isSystemField() {
        return new Integer(0).equals(this.getSign());
    }

    public boolean isCustomField() {
        return new Integer(1).equals(this.getSign());
    }
}

