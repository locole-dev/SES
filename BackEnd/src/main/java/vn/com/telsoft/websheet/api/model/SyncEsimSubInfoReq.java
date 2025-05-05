package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SyncEsimSubInfoReq {

    @JsonProperty("Content-Type")
    private String contentType;

    @JsonProperty("x-transaction-id")
    private String xTransactionId;

    private String pairedImsi;
    private String pairedSimType;
    private String pairedMsisdn;
    private String pairedIccid;
    private Integer subType;
    private String eid;
    private String imei;
    private String iccid;
    private String imsi;
    private Integer msisdn;
    private String deviceType;
    private String operatorId;
    private String applicationCategory;

    public SyncEsimSubInfoReq() {
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getxTransactionId() {
        return xTransactionId;
    }

    public void setxTransactionId(String xTransactionId) {
        this.xTransactionId = xTransactionId;
    }

    public String getPairedImsi() {
        return pairedImsi;
    }

    public void setPairedImsi(String pairedImsi) {
        this.pairedImsi = pairedImsi;
    }

    public String getPairedSimType() {
        return pairedSimType;
    }

    public void setPairedSimType(String pairedSimType) {
        this.pairedSimType = pairedSimType;
    }

    public String getPairedMsisdn() {
        return pairedMsisdn;
    }

    public void setPairedMsisdn(String pairedMsisdn) {
        this.pairedMsisdn = pairedMsisdn;
    }

    public String getPairedIccid() {
        return pairedIccid;
    }

    public void setPairedIccid(String pairedIccid) {
        this.pairedIccid = pairedIccid;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public Integer getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(Integer msisdn) {
        this.msisdn = msisdn;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getApplicationCategory() {
        return applicationCategory;
    }

    public void setApplicationCategory(String applicationCategory) {
        this.applicationCategory = applicationCategory;
    }
}
