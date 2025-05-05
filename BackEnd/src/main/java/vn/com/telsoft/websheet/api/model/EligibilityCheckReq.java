package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EligibilityCheckReq {

    @JsonProperty("primary-imsi")
    private String primaryImsi;

    @JsonProperty("primary-msisdn")
    private String primaryMsisdn;

    private String eid;
    private String imei;

    @JsonProperty("number-plan-type")
    private String numberPlanType;

    public EligibilityCheckReq() {
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

    public String getNumberPlanType() {
        return numberPlanType;
    }

    public void setNumberPlanType(String numberPlanType) {
        this.numberPlanType = numberPlanType;
    }
}
