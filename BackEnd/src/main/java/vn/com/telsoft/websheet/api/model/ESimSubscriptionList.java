package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ESimSubscriptionList {
    private String subId;
    private Integer subType;
    private String iccid;
    private String matchingId;
    private String eid;
    private Integer isPos;
    private String imei;
    private String meid;
    private String deviceType;
    private String displayName;
    private String selectedPlanId;
    private String primaryImsi;
    private String primaryMsisdn;
    private String imsi;
    private String msisdn;
    private String iccidProfileType;
    private String activationStatus;
    private String iccidStatus;
    private Integer lockStatus;
    private String alternateSmdpFqdn;
    private String simAllianceProfileVersion;
    private String gsmaSgp22Version;
    private String billingAccountNumber;

    @JsonProperty("activateBy")
    private String activatedBy;

    private Boolean useDs;
    private Integer lastModifiedTime;

    public ESimSubscriptionList() {
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getMatchingId() {
        return matchingId;
    }

    public void setMatchingId(String matchingId) {
        this.matchingId = matchingId;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public Integer getIsPos() {
        return isPos;
    }

    public void setIsPos(Integer isPos) {
        this.isPos = isPos;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSelectedPlanId() {
        return selectedPlanId;
    }

    public void setSelectedPlanId(String selectedPlanId) {
        this.selectedPlanId = selectedPlanId;
    }

    public String getPrimaryImsi() {
        return primaryImsi;
    }

    public void setPrimaryImsi(String primaryImsi) {
        this.primaryImsi = primaryImsi;
    }

    public String getPrimaryMsisdn() {
        return primaryMsisdn;
    }

    public void setPrimaryMsisdn(String primaryMsisdn) {
        this.primaryMsisdn = primaryMsisdn;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getIccidProfileType() {
        return iccidProfileType;
    }

    public void setIccidProfileType(String iccidProfileType) {
        this.iccidProfileType = iccidProfileType;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getIccidStatus() {
        return iccidStatus;
    }

    public void setIccidStatus(String iccidStatus) {
        this.iccidStatus = iccidStatus;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getAlternateSmdpFqdn() {
        return alternateSmdpFqdn;
    }

    public void setAlternateSmdpFqdn(String alternateSmdpFqdn) {
        this.alternateSmdpFqdn = alternateSmdpFqdn;
    }

    public String getSimAllianceProfileVersion() {
        return simAllianceProfileVersion;
    }

    public void setSimAllianceProfileVersion(String simAllianceProfileVersion) {
        this.simAllianceProfileVersion = simAllianceProfileVersion;
    }

    public String getGsmaSgp22Version() {
        return gsmaSgp22Version;
    }

    public void setGsmaSgp22Version(String gsmaSgp22Version) {
        this.gsmaSgp22Version = gsmaSgp22Version;
    }

    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    public void setBillingAccountNumber(String billingAccountNumber) {
        this.billingAccountNumber = billingAccountNumber;
    }

    public String getActivatedBy() {
        return activatedBy;
    }

    public void setActivatedBy(String activatedBy) {
        this.activatedBy = activatedBy;
    }

    public Boolean getUseDs() {
        return useDs;
    }

    public void setUseDs(Boolean useDs) {
        this.useDs = useDs;
    }

    public Integer getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Integer lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
