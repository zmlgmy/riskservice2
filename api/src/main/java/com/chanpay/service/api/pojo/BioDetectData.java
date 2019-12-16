package com.chanpay.service.api.pojo;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-12 17:54
 * @Description:
 */

import sun.font.Script;

import java.io.Serializable;
import java.util.List;

public class BioDetectData implements Serializable {
    private static final long serialVersionUID = -5777324337710532406L;
    private String requestGuid;
    private long loadTime;
    private String appKey;
    private String requestToken;
    private String userAgent;
    private String platfrom;
    private int timeZone;
    private String language;
    private List<String> plugins;
    private List<String> fonts;
    private String url;
    private String referrer;
    private int historyLength;
    private int hash;
    private int screenLeft;
    private int screenTop;
    private int clientWidth;
    private int clientHeight;
    private int screenWidth;
    private int screenHeight;
    private int availWidth;
    private int availHeight;
    private int iframeCount;
    private int formCount;
    private int inputCount;
    private int scriptCount;
    private int imageCount;
    private List<Script> scripts;
    private List<MouseEvent> mouseEvents;
    private List<KeyboardEvent> keyboardEvents;
    private List<WindowEvent> windowEvents;
    private List<Element> elements;
    private List<CrackInfo> cracks;
    private long imgRequestTime;
    private long imgResponseTime;
    private boolean containsRequestInformation;
    private boolean containsBrowserSpecification;
    private boolean containsDisplaySpecification;
    private boolean containsPluginList;
    private boolean containsFontList;
    private boolean containsElementHistogram;
    private boolean containsScriptList;
    private boolean containsElementSpecification;
    private boolean containsPageSpecification;
    private boolean containsImageRequestInformation;
    private boolean containsMouseEvent;
    private boolean containsKeyboardEvent;
    private boolean containsWindowEvent;
    private boolean containsCrackInformation;

    public BioDetectData() {
    }

    public String getRequestGuid() {
        return this.requestGuid;
    }

    public void setRequestGuid(String requestGuid) {
        this.requestGuid = requestGuid;
    }

    public long getLoadTime() {
        return this.loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getRequestToken() {
        return this.requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getPlatfrom() {
        return this.platfrom;
    }

    public void setPlatfrom(String platfrom) {
        this.platfrom = platfrom;
    }

    public int getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getPlugins() {
        return this.plugins;
    }

    public void setPlugins(List<String> plugins) {
        this.plugins = plugins;
    }

    public List<String> getFonts() {
        return this.fonts;
    }

    public void setFonts(List<String> fonts) {
        this.fonts = fonts;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferrer() {
        return this.referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public int getHistoryLength() {
        return this.historyLength;
    }

    public void setHistoryLength(int historyLength) {
        this.historyLength = historyLength;
    }

    public int getHash() {
        return this.hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public int getScreenLeft() {
        return this.screenLeft;
    }

    public void setScreenLeft(int screenLeft) {
        this.screenLeft = screenLeft;
    }

    public int getScreenTop() {
        return this.screenTop;
    }

    public void setScreenTop(int screenTop) {
        this.screenTop = screenTop;
    }

    public int getClientWidth() {
        return this.clientWidth;
    }

    public void setClientWidth(int clientWidth) {
        this.clientWidth = clientWidth;
    }

    public int getClientHeight() {
        return this.clientHeight;
    }

    public void setClientHeight(int clientHeight) {
        this.clientHeight = clientHeight;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getAvailWidth() {
        return this.availWidth;
    }

    public void setAvailWidth(int availWidth) {
        this.availWidth = availWidth;
    }

    public int getAvailHeight() {
        return this.availHeight;
    }

    public void setAvailHeight(int availHeight) {
        this.availHeight = availHeight;
    }

    public int getIframeCount() {
        return this.iframeCount;
    }

    public void setIframeCount(int iframeCount) {
        this.iframeCount = iframeCount;
    }

    public int getFormCount() {
        return this.formCount;
    }

    public void setFormCount(int formCount) {
        this.formCount = formCount;
    }

    public int getInputCount() {
        return this.inputCount;
    }

    public void setInputCount(int inputCount) {
        this.inputCount = inputCount;
    }

    public int getScriptCount() {
        return this.scriptCount;
    }

    public void setScriptCount(int scriptCount) {
        this.scriptCount = scriptCount;
    }

    public int getImageCount() {
        return this.imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public List<Script> getScripts() {
        return this.scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }

    public List<MouseEvent> getMouseEvents() {
        return this.mouseEvents;
    }

    public void setMouseEvents(List<MouseEvent> mouseEvents) {
        this.mouseEvents = mouseEvents;
    }

    public List<KeyboardEvent> getKeyboardEvents() {
        return this.keyboardEvents;
    }

    public void setKeyboardEvents(List<KeyboardEvent> keyboardEvents) {
        this.keyboardEvents = keyboardEvents;
    }

    public List<WindowEvent> getWindowEvents() {
        return this.windowEvents;
    }

    public void setWindowEvents(List<WindowEvent> windowEvents) {
        this.windowEvents = windowEvents;
    }

    public List<Element> getElements() {
        return this.elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<CrackInfo> getCracks() {
        return this.cracks;
    }

    public void setCracks(List<CrackInfo> cracks) {
        this.cracks = cracks;
    }

    public long getImgRequestTime() {
        return this.imgRequestTime;
    }

    public void setImgRequestTime(long imgRequestTime) {
        this.imgRequestTime = imgRequestTime;
    }

    public long getImgResponseTime() {
        return this.imgResponseTime;
    }

    public void setImgResponseTime(long imgResponseTime) {
        this.imgResponseTime = imgResponseTime;
    }

    public boolean isContainsRequestInformation() {
        return this.containsRequestInformation;
    }

    public void setContainsRequestInformation(boolean containsRequestInformation) {
        this.containsRequestInformation = containsRequestInformation;
    }

    public boolean isContainsBrowserSpecification() {
        return this.containsBrowserSpecification;
    }

    public void setContainsBrowserSpecification(boolean containsBrowserSpecification) {
        this.containsBrowserSpecification = containsBrowserSpecification;
    }

    public boolean isContainsDisplaySpecification() {
        return this.containsDisplaySpecification;
    }

    public void setContainsDisplaySpecification(boolean containsDisplaySpecification) {
        this.containsDisplaySpecification = containsDisplaySpecification;
    }

    public boolean isContainsPluginList() {
        return this.containsPluginList;
    }

    public void setContainsPluginList(boolean containsPluginList) {
        this.containsPluginList = containsPluginList;
    }

    public boolean isContainsFontList() {
        return this.containsFontList;
    }

    public void setContainsFontList(boolean containsFontList) {
        this.containsFontList = containsFontList;
    }

    public boolean isContainsElementHistogram() {
        return this.containsElementHistogram;
    }

    public void setContainsElementHistogram(boolean containsElementHistogram) {
        this.containsElementHistogram = containsElementHistogram;
    }

    public boolean isContainsScriptList() {
        return this.containsScriptList;
    }

    public void setContainsScriptList(boolean containsScriptList) {
        this.containsScriptList = containsScriptList;
    }

    public boolean isContainsElementSpecification() {
        return this.containsElementSpecification;
    }

    public void setContainsElementSpecification(boolean containsElementSpecification) {
        this.containsElementSpecification = containsElementSpecification;
    }

    public boolean isContainsPageSpecification() {
        return this.containsPageSpecification;
    }

    public void setContainsPageSpecification(boolean containsPageSpecification) {
        this.containsPageSpecification = containsPageSpecification;
    }

    public boolean isContainsImageRequestInformation() {
        return this.containsImageRequestInformation;
    }

    public void setContainsImageRequestInformation(boolean containsImageRequestInformation) {
        this.containsImageRequestInformation = containsImageRequestInformation;
    }

    public boolean isContainsMouseEvent() {
        return this.containsMouseEvent;
    }

    public void setContainsMouseEvent(boolean containsMouseEvent) {
        this.containsMouseEvent = containsMouseEvent;
    }

    public boolean isContainsKeyboardEvent() {
        return this.containsKeyboardEvent;
    }

    public void setContainsKeyboardEvent(boolean containsKeyboardEvent) {
        this.containsKeyboardEvent = containsKeyboardEvent;
    }

    public boolean isContainsWindowEvent() {
        return this.containsWindowEvent;
    }

    public void setContainsWindowEvent(boolean containsWindowEvent) {
        this.containsWindowEvent = containsWindowEvent;
    }

    public boolean isContainsCrackInformation() {
        return this.containsCrackInformation;
    }

    public void setContainsCrackInformation(boolean containsCrackInformation) {
        this.containsCrackInformation = containsCrackInformation;
    }
}

