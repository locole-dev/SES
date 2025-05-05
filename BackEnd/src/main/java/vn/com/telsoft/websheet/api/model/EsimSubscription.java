package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EsimSubscription {

    @JsonProperty("iccid")
    private String iccid;
    @JsonProperty("eid")
    private String eid;
    @JsonProperty("isPos")
    private Integer isPos;
    @JsonProperty("primaryImsi")
    private String primaryImsi;
    @JsonProperty("primaryMsisdn")
    private String primaryMsisdn;
    @JsonProperty("imsi")
    private String imsi;
    @JsonProperty("msisdn")
    private String msisdn;
    @JsonProperty("iccidProfileType")
    private String iccidProfileType;
    @JsonProperty("provisionStartTime")
    private Integer provisionStartTime;
    @JsonProperty("provisionDeactivateTime")
    private Integer provisionDeactivateTime;
    @JsonProperty("activationStatus")
    private String activationStatus;
    @JsonProperty("iccidStatus")
    private String iccidStatus;
    @JsonProperty("lockStatus")
    private Integer lockStatus;
    @JsonProperty("applicationCategory")
    private String applicationCategory;
    @JsonProperty("activationMode")
    private Integer activationMode;
    @JsonProperty("serviceType")
    private Integer serviceType;
    @JsonProperty("lastUpdateTime")
    private String lastUpdateTime;

    @JsonProperty("iccid")
    public String getIccid() {
        return iccid;
    }

    @JsonProperty("iccid")
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    @JsonProperty("eid")
    public String getEid() {
        return eid;
    }

    @JsonProperty("eid")
    public void setEid(String eid) {
        this.eid = eid;
    }

    @JsonProperty("isPos")
    public Integer getIsPos() {
        return isPos;
    }

    @JsonProperty("isPos")
    public void setIsPos(Integer isPos) {
        this.isPos = isPos;
    }

    @JsonProperty("primaryImsi")
    public String getPrimaryImsi() {
        return primaryImsi;
    }

    @JsonProperty("primaryImsi")
    public void setPrimaryImsi(String primaryImsi) {
        this.primaryImsi = primaryImsi;
    }

    @JsonProperty("primaryMsisdn")
    public String getPrimaryMsisdn() {
        return primaryMsisdn;
    }

    @JsonProperty("primaryMsisdn")
    public void setPrimaryMsisdn(String primaryMsisdn) {
        this.primaryMsisdn = primaryMsisdn;
    }

    @JsonProperty("imsi")
    public String getImsi() {
        return imsi;
    }

    @JsonProperty("imsi")
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    @JsonProperty("msisdn")
    public String getMsisdn() {
        return msisdn;
    }

    @JsonProperty("msisdn")
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @JsonProperty("iccidProfileType")
    public String getIccidProfileType() {
        return iccidProfileType;
    }

    @JsonProperty("iccidProfileType")
    public void setIccidProfileType(String iccidProfileType) {
        this.iccidProfileType = iccidProfileType;
    }

    @JsonProperty("provisionStartTime")
    public Integer getProvisionStartTime() {
        return provisionStartTime;
    }

    @JsonProperty("provisionStartTime")
    public void setProvisionStartTime(Integer provisionStartTime) {
        this.provisionStartTime = provisionStartTime;
    }

    @JsonProperty("provisionDeactivateTime")
    public Integer getProvisionDeactivateTime() {
        return provisionDeactivateTime;
    }

    @JsonProperty("provisionDeactivateTime")
    public void setProvisionDeactivateTime(Integer provisionDeactivateTime) {
        this.provisionDeactivateTime = provisionDeactivateTime;
    }

    @JsonProperty("activationStatus")
    public String getActivationStatus() {
        return activationStatus;
    }

    @JsonProperty("activationStatus")
    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

    @JsonProperty("iccidStatus")
    public String getIccidStatus() {
        return iccidStatus;
    }

    @JsonProperty("iccidStatus")
    public void setIccidStatus(String iccidStatus) {
        this.iccidStatus = iccidStatus;
    }

    @JsonProperty("lockStatus")
    public Integer getLockStatus() {
        return lockStatus;
    }

    @JsonProperty("lockStatus")
    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    @JsonProperty("applicationCategory")
    public String getApplicationCategory() {
        return applicationCategory;
    }

    @JsonProperty("applicationCategory")
    public void setApplicationCategory(String applicationCategory) {
        this.applicationCategory = applicationCategory;
    }

    @JsonProperty("activationMode")
    public Integer getActivationMode() {
        return activationMode;
    }

    @JsonProperty("activationMode")
    public void setActivationMode(Integer activationMode) {
        this.activationMode = activationMode;
    }

    @JsonProperty("serviceType")
    public Integer getServiceType() {
        return serviceType;
    }

    @JsonProperty("serviceType")
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    @JsonProperty("lastUpdateTime")
    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    @JsonProperty("lastUpdateTime")
    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EsimSubscription.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("iccid");
        sb.append('=');
        sb.append(((this.iccid == null)?"<null>":this.iccid));
        sb.append(',');
        sb.append("eid");
        sb.append('=');
        sb.append(((this.eid == null)?"<null>":this.eid));
        sb.append(',');
        sb.append("isPos");
        sb.append('=');
        sb.append(((this.isPos == null)?"<null>":this.isPos));
        sb.append(',');
        sb.append("primaryImsi");
        sb.append('=');
        sb.append(((this.primaryImsi == null)?"<null>":this.primaryImsi));
        sb.append(',');
        sb.append("primaryMsisdn");
        sb.append('=');
        sb.append(((this.primaryMsisdn == null)?"<null>":this.primaryMsisdn));
        sb.append(',');
        sb.append("imsi");
        sb.append('=');
        sb.append(((this.imsi == null)?"<null>":this.imsi));
        sb.append(',');
        sb.append("msisdn");
        sb.append('=');
        sb.append(((this.msisdn == null)?"<null>":this.msisdn));
        sb.append(',');
        sb.append("iccidProfileType");
        sb.append('=');
        sb.append(((this.iccidProfileType == null)?"<null>":this.iccidProfileType));
        sb.append(',');
        sb.append("provisionStartTime");
        sb.append('=');
        sb.append(((this.provisionStartTime == null)?"<null>":this.provisionStartTime));
        sb.append(',');
        sb.append("provisionDeactivateTime");
        sb.append('=');
        sb.append(((this.provisionDeactivateTime == null)?"<null>":this.provisionDeactivateTime));
        sb.append(',');
        sb.append("activationStatus");
        sb.append('=');
        sb.append(((this.activationStatus == null)?"<null>":this.activationStatus));
        sb.append(',');
        sb.append("iccidStatus");
        sb.append('=');
        sb.append(((this.iccidStatus == null)?"<null>":this.iccidStatus));
        sb.append(',');
        sb.append("lockStatus");
        sb.append('=');
        sb.append(((this.lockStatus == null)?"<null>":this.lockStatus));
        sb.append(',');
        sb.append("applicationCategory");
        sb.append('=');
        sb.append(((this.applicationCategory == null)?"<null>":this.applicationCategory));
        sb.append(',');
        sb.append("activationMode");
        sb.append('=');
        sb.append(((this.activationMode == null)?"<null>":this.activationMode));
        sb.append(',');
        sb.append("serviceType");
        sb.append('=');
        sb.append(((this.serviceType == null)?"<null>":this.serviceType));
        sb.append(',');
        sb.append("lastUpdateTime");
        sb.append('=');
        sb.append(((this.lastUpdateTime == null)?"<null>":this.lastUpdateTime));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
