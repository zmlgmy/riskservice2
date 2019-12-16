package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 17:49
 * @Description:
 */

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
@Slf4j
public class FraudContext implements Serializable, Cloneable {
    private static final long serialVersionUID = -3320502733559293390L;
    private static final Field[] fields = FraudContext.class.getDeclaredFields();
    public String partnerCode;
    public String appName;
    public String appType;
    public String secretKey;
    public String eventId;
    public String eventType;
    public Date eventOccurTime;
    public String tokenId;
    public String cookieId;
    public String sessionId;
    public String deviceId;
    public String smartId;
    public String mac;
    public String ipAddress;
    public String ip3;
    public String gpsAddress;
    public String startingPointGpsAddress;
    public String endingPointGpsAddress;
    public String bdString;
    public BioDetectData bdData;
    public String bdRequestId;
    public String bdReason;
    public GeoipEntity geoipEntity;
    public String billingAddressStreet;
    public String billingAddressCounty;
    public String billingAddressCity;
    public String billingAddressProvince;
    public String billingAddressCountry;
    public String billingZipCode;
    public String accountLogin;
    public String accountName;
    public String accountEmail;
    public String accountPhone;
    public String accountMobile;
    public String accountPassword;
    public String transactionId;
    public String accountAddressStreet;
    public String accountAddressCounty;
    public String accountAddressCity;
    public String accountAddressProvince;
    public String accountAddressCountry;
    public String accountZipCode;
    public String deliverName;
    public String deliverMobile;
    public String deliverPhone;
    public String deliverAddressStreet;
    public String deliverAddressCounty;
    public String deliverAddressCity;
    public String deliverAddressProvince;
    public String deliverAddressCountry;
    public String deliverZipCode;
    public String payId;
    public String payMethod;
    public Double payAmount;
    public String payCurrency;
    public String ccBin;
    public String ccPhone;
    public Integer ccExpirationYear;
    public Integer ccExpirationMonth;
    public String idNumber;
    public String idNumberShort;
    public String organization;
    public String unitCode;
    public String userAgentCust;
    public String referCust;
    public String cardNumber;
    public String cardType;
    public String items;
    public Integer itemsCount;
    public String payeeUserid;
    public String payeeName;
    public String payeeEmail;
    public String payeeMobile;
    public String payeePhone;
    public String payeeIdNumber;
    public String payeeCardNumber;
    public ConcurrentMap<String, Object> executeTimeMap = new ConcurrentHashMap();
    private ConcurrentMap<String, List<JSONObject>> velocityMap = new ConcurrentHashMap();
    private ConcurrentMap<String, Object> velocityRuleResultMap = new ConcurrentHashMap();
    public String bizLicense;
    public String orgCode;
    private String idNumberHash;
    private String accountEmailHash;
    private String accountPhoneHash;
    private String idNumberArea;
    private String cardNumberHash;
    private String cardNumberArea;
    private String accountMobileHash;
    private String accountMobileArea;
    private String accountBalance;
    private String state;
    private String cardName;
    private String cardCity;
    private Date accountBirthday;
    private String accountGender;
    private String accountNickname;
    private String ruleType;
    private String ruleTypeName;
    private String ruleExplanation;
    private String saasSeqId;
    private String apiServiceIp;
    private String interventType = "middle";
    private List<RmlReportMessage> amlReport = Collections.synchronizedList(new ArrayList());
    public Map<String, Object> systemFiels = new HashMap();
    private long requestTraceStartTime;
    private ConcurrentMap<String, Object> ruleDetailDataMap = new ConcurrentHashMap();
    public Set<String> ifRuleResult = new ConcurrentHashSet();
    private List<String> immunoRuleList = null;
    public Map<String, Scope> dimTypes;
    private Map<String, CounterRuleInfo> counterRuleInfoResultMap = new ConcurrentHashMap();
    private String sequenceId;
    public Map<String, Object> deviceInfo = new HashMap();
    public Map<String, Object> customFields = new HashMap();
    private Set<String> velocityItemKeySet;
    private String status;

    public FraudContext() {
    }

    public List<RmlReportMessage> getAmlReport() {
        return this.amlReport;
    }

    public void putAmlReport(RmlReportMessage rmlReportMessage) {
        this.amlReport.add(rmlReportMessage);
    }

    public String getApiServiceIp() {
        return this.apiServiceIp;
    }

    public void setApiServiceIp(String apiServiceIp) {
        this.apiServiceIp = apiServiceIp;
    }

    public String getInterventType() {
        return this.interventType;
    }

    public void setInterventType(String interventType) {
        this.interventType = interventType;
    }

    public String getSaasSeqId() {
        return this.saasSeqId;
    }

    public void setSaasSeqId(String saasSeqId) {
        this.saasSeqId = saasSeqId;
    }

    public String getRuleTypeName() {
        return this.ruleTypeName;
    }

    public void setRuleTypeName(String ruleTypeName) {
        this.ruleTypeName = ruleTypeName;
    }

    public String getRuleType() {
        return this.ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleExplanation() {
        return this.ruleExplanation;
    }

    public void setRuleExplanation(String ruleExplanation) {
        this.ruleExplanation = ruleExplanation;
    }

    public List<String> getImmunoRuleList() {
        return this.immunoRuleList;
    }

    public void setImmunoRuleList(List<String> immunoRuleList) {
        this.immunoRuleList = immunoRuleList;
    }

    public boolean addIfRuleResult(String ruleUuid) {
        if (StringUtils.isNotBlank(ruleUuid) && null != this.ifRuleResult) {
            this.ifRuleResult.add(ruleUuid);
        }

        return true;
    }

    public boolean getIfRuleResult(String ruleUuid) {
        return null != this.ifRuleResult && !StringUtils.isBlank(ruleUuid) ? this.ifRuleResult.contains(ruleUuid) : false;
    }

    public void clearIfRuleResult() {
        if (null != this.ifRuleResult) {
            this.ifRuleResult.clear();
            this.ifRuleResult = null;
        }
    }

    public void putRuleDetailData(String key, Object value) {
        this.ruleDetailDataMap.put(key, value);
    }

    public void clearRuleDetailData() {
        this.ruleDetailDataMap.clear();
        this.ruleDetailDataMap = null;
    }

    public Object getRuleDetailData(String key) {
        return this.ruleDetailDataMap.get(key);
    }

    public Map<String, Object> getSystemFiels() {
        return this.systemFiels;
    }

    public void setSystemFiels(Map<String, Object> systemFiels) {
        this.systemFiels = systemFiels;
    }

    public String getSequenceId() {
        return this.sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public List<JSONObject> getVelocityData(String key) {
        return (List)this.velocityMap.get(key);
    }

    public void putVelocityData(String key, List<JSONObject> data) {
        if (null != this.velocityMap) {
            this.velocityMap.put(key, data);
        }

    }

    public boolean containsVelocityData(String key) {
        return null == this.velocityMap ? false : this.velocityMap.containsKey(key);
    }

    public void clearVelocityData() {
        this.velocityMap.clear();
        this.velocityMap = null;
    }

    public Object getVelocityRuleResult(String key) {
        return null == this.velocityRuleResultMap ? null : this.velocityRuleResultMap.get(key);
    }

    public void putVelocityRuleResult(String key, Object value) {
        if (null != this.velocityRuleResultMap) {
            this.velocityRuleResultMap.put(key, value);
        }

    }

    public void clearVelocityRuleResult() {
        this.velocityRuleResultMap.clear();
        this.velocityRuleResultMap = null;
    }

    public String getBizLicense() {
        return this.bizLicense;
    }

    public void setBizLicense(String bizLicense) {
        this.bizLicense = bizLicense;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getBdString() {
        return this.bdString;
    }

    public void setBdString(String bdString) {
        this.bdString = bdString;
    }

    public BioDetectData getBdData() {
        return this.bdData;
    }

    public void setBdData(BioDetectData bdData) {
        this.bdData = bdData;
    }

    public String getBdRequestId() {
        return this.bdRequestId;
    }

    public void setBdRequestId(String bdRequestId) {
        this.bdRequestId = bdRequestId;
    }

    public String getBdReason() {
        return this.bdReason;
    }

    public void setBdReason(String bdReason) {
        this.bdReason = bdReason;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Integer getItemsCount() {
        return this.itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public String getPayeeUserid() {
        return this.payeeUserid;
    }

    public void setPayeeUserid(String payeeUserid) {
        this.payeeUserid = payeeUserid;
    }

    public String getPayeeName() {
        return this.payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeEmail() {
        return this.payeeEmail;
    }

    public void setPayeeEmail(String payeeEmail) {
        this.payeeEmail = payeeEmail;
    }

    public String getPayeeMobile() {
        return this.payeeMobile;
    }

    public void setPayeeMobile(String payeeMobile) {
        this.payeeMobile = payeeMobile;
    }

    public String getPayeePhone() {
        return this.payeePhone;
    }

    public void setPayeePhone(String payeePhone) {
        this.payeePhone = payeePhone;
    }

    public String getPayeeIdNumber() {
        return this.payeeIdNumber;
    }

    public void setPayeeIdNumber(String payeeIdNumber) {
        this.payeeIdNumber = payeeIdNumber;
    }

    public String getPayeeCardNumber() {
        return this.payeeCardNumber;
    }

    public void setPayeeCardNumber(String payeeCardNumber) {
        this.payeeCardNumber = payeeCardNumber;
    }

    public String getUserAgentCust() {
        return this.userAgentCust;
    }

    public void setUserAgentCust(String userAgentCust) {
        this.userAgentCust = userAgentCust;
    }

    public String getReferCust() {
        return this.referCust;
    }

    public void setReferCust(String referCust) {
        this.referCust = referCust;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumberShort() {
        return this.idNumberShort;
    }

    public void setIdNumberShort(String idNumberShort) {
        this.idNumberShort = idNumberShort;
    }

    public long getRequestTraceStartTime() {
        return this.requestTraceStartTime;
    }

    public void setRequestTraceStartTime(long requestTraceStartTime) {
        this.requestTraceStartTime = requestTraceStartTime;
    }

    public Set<String> getVelocityItemKeySet() {
        return this.velocityItemKeySet;
    }

    public void setVelocityItemKeySet(Set<String> velocityItemKeySet) {
        this.velocityItemKeySet = velocityItemKeySet;
    }

    public Object get(String key) {
        if (key == null) {
            return null;
        } else if ("null".equals(key)) {
            return "null";
        } else {
            Field[] var2 = fields;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Field field = var2[var4];

                try {
                    String name = field.getName();
                    if (key.equals(name)) {
                        return field.get(this);
                    }
                } catch (Exception var7) {
                }
            }

            Object sysVal = this.systemFiels.get(key);
            if (sysVal != null) {
                return sysVal;
            } else {
                if (null != this.deviceInfo) {
                    Object val = this.deviceInfo.get(key);
                    if (val != null) {
                        return val;
                    }
                }

                return this.customFields.get(key);
            }
        }
    }

    public void setFieldValue(String key, Object value) {
        if (!StringUtils.isBlank(key)) {
            if (key.startsWith("ext_")) {
                this.customFields.put(key, value);
            } else {
                this.systemFiels.put(key, value);
            }

        }
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPartnerCode() {
        return this.partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getCookieId() {
        return this.cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIp3() {
        return this.ip3;
    }

    public void setIp3(String ip3) {
        this.ip3 = ip3;
    }

    public String getGpsAddress() {
        return this.gpsAddress;
    }

    public void setGpsAddress(String gpsAddress) {
        this.gpsAddress = gpsAddress;
    }

    public String getStartingPointGpsAddress() {
        return this.startingPointGpsAddress;
    }

    public void setStartingPointGpsAddress(String startingPointGpsAddress) {
        this.startingPointGpsAddress = startingPointGpsAddress;
    }

    public String getEndingPointGpsAddress() {
        return this.endingPointGpsAddress;
    }

    public void setEndingPointGpsAddress(String endingPointGpsAddress) {
        this.endingPointGpsAddress = endingPointGpsAddress;
    }

    public String getAccountLogin() {
        return this.accountLogin;
    }

    public void setAccountLogin(String accountLogin) {
        this.accountLogin = accountLogin;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountEmail() {
        return this.accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPhone() {
        return this.accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public String getAccountMobile() {
        return this.accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getAccountPassword() {
        return this.accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayId() {
        return this.payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Double getPayAmount() {
        return this.payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayCurrency() {
        return this.payCurrency;
    }

    public void setPayCurrency(String payCurrency) {
        this.payCurrency = payCurrency;
    }

    public String getCcBin() {
        return this.ccBin;
    }

    public void setCcBin(String ccBin) {
        this.ccBin = ccBin;
    }

    public String getCcPhone() {
        return this.ccPhone;
    }

    public void setCcPhone(String ccPhone) {
        this.ccPhone = ccPhone;
    }

    public Integer getCcExpirationYear() {
        return this.ccExpirationYear;
    }

    public void setCcExpirationYear(Integer ccExpirationYear) {
        this.ccExpirationYear = ccExpirationYear;
    }

    public Integer getCcExpirationMonth() {
        return this.ccExpirationMonth;
    }

    public void setCcExpirationMonth(Integer ccExpirationMonth) {
        this.ccExpirationMonth = ccExpirationMonth;
    }

    public Map<String, Object> getCustomFields() {
        return this.customFields;
    }

    public void setCustomFields(Map<String, Object> customFields) {
        this.customFields = customFields;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSmartId() {
        return this.smartId;
    }

    public void setSmartId(String smartId) {
        this.smartId = smartId;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAccountAddressStreet() {
        return this.accountAddressStreet;
    }

    public void setAccountAddressStreet(String accountAddressStreet) {
        this.accountAddressStreet = accountAddressStreet;
    }

    public String getAccountAddressCounty() {
        return this.accountAddressCounty;
    }

    public void setAccountAddressCounty(String accountAddressRegion) {
        this.accountAddressCounty = accountAddressRegion;
    }

    public String getAccountAddressCity() {
        return this.accountAddressCity;
    }

    public void setAccountAddressCity(String accountAddressCity) {
        this.accountAddressCity = accountAddressCity;
    }

    public String getAccountAddressProvince() {
        return this.accountAddressProvince;
    }

    public void setAccountAddressProvince(String accountAddressProvince) {
        this.accountAddressProvince = accountAddressProvince;
    }

    public String getAccountAddressCountry() {
        return this.accountAddressCountry;
    }

    public void setAccountAddressCountry(String accountAddressCountry) {
        this.accountAddressCountry = accountAddressCountry;
    }

    public String getAccountZipCode() {
        return this.accountZipCode;
    }

    public void setAccountZipCode(String accountZipCode) {
        this.accountZipCode = accountZipCode;
    }

    public String getDeliverName() {
        return this.deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverMobile() {
        return this.deliverMobile;
    }

    public void setDeliverMobile(String deliverMobile) {
        this.deliverMobile = deliverMobile;
    }

    public String getDeliverPhone() {
        return this.deliverPhone;
    }

    public void setDeliverPhone(String deliverPhone) {
        this.deliverPhone = deliverPhone;
    }

    public String getDeliverAddressStreet() {
        return this.deliverAddressStreet;
    }

    public void setDeliverAddressStreet(String deliverAddressStreet) {
        this.deliverAddressStreet = deliverAddressStreet;
    }

    public String getDeliverAddressCounty() {
        return this.deliverAddressCounty;
    }

    public void setDeliverAddressCounty(String deliverAddressRegion) {
        this.deliverAddressCounty = deliverAddressRegion;
    }

    public String getDeliverAddressCity() {
        return this.deliverAddressCity;
    }

    public void setDeliverAddressCity(String deliverAddressCity) {
        this.deliverAddressCity = deliverAddressCity;
    }

    public String getDeliverAddressProvince() {
        return this.deliverAddressProvince;
    }

    public void setDeliverAddressProvince(String deliverAddressProvince) {
        this.deliverAddressProvince = deliverAddressProvince;
    }

    public String getDeliverAddressCountry() {
        return this.deliverAddressCountry;
    }

    public void setDeliverAddressCountry(String deliverAddressCountry) {
        this.deliverAddressCountry = deliverAddressCountry;
    }

    public Map<String, Object> getDeviceInfo() {
        return this.deviceInfo;
    }

    public void setDeviceInfo(Map<String, Object> deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getBillingAddressStreet() {
        return this.billingAddressStreet;
    }

    public void setBillingAddressStreet(String billingAddressStreet) {
        this.billingAddressStreet = billingAddressStreet;
    }

    public String getBillingAddressCounty() {
        return this.billingAddressCounty;
    }

    public void setBillingAddressCounty(String billingAddressCounty) {
        this.billingAddressCounty = billingAddressCounty;
    }

    public String getBillingAddressCity() {
        return this.billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressProvince() {
        return this.billingAddressProvince;
    }

    public void setBillingAddressProvince(String billingAddressProvince) {
        this.billingAddressProvince = billingAddressProvince;
    }

    public String getBillingAddressCountry() {
        return this.billingAddressCountry;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }

    public String getBillingZipCode() {
        return this.billingZipCode;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public String getAppType() {
        return this.appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDeliverZipCode() {
        return this.deliverZipCode;
    }

    public void setDeliverZipCode(String deliverZipCode) {
        this.deliverZipCode = deliverZipCode;
    }

    public GeoipEntity getGeoipEntity() {
        return this.geoipEntity;
    }

    public void setGeoipEntity(GeoipEntity geoipEntity) {
        this.geoipEntity = geoipEntity;
    }

    public Date getEventOccurTime() {
        return this.eventOccurTime;
    }

    public void setEventOccurTime(Date eventOccurTime) {
        this.eventOccurTime = eventOccurTime;
    }

    public String getUnitCode() {
        return this.unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getIdNumberHash() {
        return this.idNumberHash;
    }

    public void setIdNumberHash(String idNumberHash) {
        this.idNumberHash = idNumberHash;
    }

    public String getIdNumberArea() {
        return this.idNumberArea;
    }

    public void setIdNumberArea(String idNumberArea) {
        this.idNumberArea = idNumberArea;
    }

    public String getCardNumberHash() {
        return this.cardNumberHash;
    }

    public void setCardNumberHash(String cardNumberHash) {
        this.cardNumberHash = cardNumberHash;
    }

    public String getCardNumberArea() {
        return this.cardNumberArea;
    }

    public void setCardNumberArea(String cardNumberArea) {
        this.cardNumberArea = cardNumberArea;
    }

    public String getAccountMobileHash() {
        return this.accountMobileHash;
    }

    public void setAccountMobileHash(String accountMobileHash) {
        this.accountMobileHash = accountMobileHash;
    }

    public String getAccountMobileArea() {
        return this.accountMobileArea;
    }

    public void setAccountMobileArea(String accountMobileArea) {
        this.accountMobileArea = accountMobileArea;
    }

    public String getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardCity() {
        return this.cardCity;
    }

    public void setCardCity(String cardCity) {
        this.cardCity = cardCity;
    }

    public Date getAccountBirthday() {
        return this.accountBirthday;
    }

    public void setAccountBirthday(Date accountBirthday) {
        this.accountBirthday = accountBirthday;
    }

    public String getAccountGender() {
        return this.accountGender;
    }

    public void setAccountGender(String accountGender) {
        this.accountGender = accountGender;
    }

    public String getAccountNickname() {
        return this.accountNickname;
    }

    public void setAccountNickname(String accountNickname) {
        this.accountNickname = accountNickname;
    }

    private boolean isPrimaryType(Class c) {
        if (c.equals(Double.class)) {
            return true;
        } else if (c.equals(Date.class)) {
            return true;
        } else if (c.equals(Integer.class)) {
            return true;
        } else if (c.equals(Long.class)) {
            return true;
        } else if (c.equals(Float.class)) {
            return true;
        } else {
            return c.equals(String.class);
        }
    }

    public JSONObject toVelocity() {
        JSONObject result = new JSONObject();
        Field[] var2 = this.getClass().getDeclaredFields();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            Class type = field.getType();
            if (!type.isArray() && this.isPrimaryType(type)) {
                try {
                    String key = field.getName();
                    Object value = field.get(this);
                    if (value != null && !(value instanceof Map)) {
                        result.put(key, value);
                    }
                } catch (Exception var9) {
                }
            }
        }

        Date eventOccurTime = this.eventOccurTime == null ? new Date() : this.eventOccurTime;
        result.put("create", eventOccurTime.getTime());
        Object value;
        Iterator var12;
        String key;
        if (this.systemFiels != null) {
            value = null;
            var12 = this.systemFiels.keySet().iterator();

            while(var12.hasNext()) {
                key = (String)var12.next();
                value = this.systemFiels.get(key);
                if (null != value) {
                    result.put(key, value);
                }
            }
        }

        if (this.customFields != null) {
            value = null;
            var12 = this.customFields.keySet().iterator();

            while(var12.hasNext()) {
                key = (String)var12.next();
                value = this.customFields.get(key);
                if (null != value) {
                    result.put(key, value);
                }
            }
        }

        return result;
    }

    public String getAccountEmailHash() {
        return this.accountEmailHash;
    }

    public void setAccountEmailHash(String accountEmailHash) {
        this.accountEmailHash = accountEmailHash;
    }

    public String getAccountPhoneHash() {
        return this.accountPhoneHash;
    }

    public void setAccountPhoneHash(String accountPhoneHash) {
        this.accountPhoneHash = accountPhoneHash;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void set(String key, Object o) {
        try {
            String methodName = "set" + com.chanpay.service.api.util.StringUtils.upperCaseFirstChar(key);
            Method method = this.getClass().getMethod(methodName, o.getClass());
            method.invoke(this, o);
        } catch (Exception var5) {
            this.systemFiels.put(key, o);
        }
    }

    public FraudContext shallowCopy() {
        try {
            return (FraudContext)this.clone();
        } catch (CloneNotSupportedException var2) {
            throw new RuntimeException("Failed to clone", var2);
        }
    }

    public void addCounterRuleInfo(String ruleId, CounterRuleInfo counterRuleInfo) {
        this.counterRuleInfoResultMap.put(ruleId, counterRuleInfo);
    }

    public CounterRuleInfo getCounterRuleInfo(String ruleId) {
        return (CounterRuleInfo)this.counterRuleInfoResultMap.get(ruleId);
    }

    public FraudContext deepCopy() {
        return (FraudContext)SerializationUtils.clone(this);
    }
}

