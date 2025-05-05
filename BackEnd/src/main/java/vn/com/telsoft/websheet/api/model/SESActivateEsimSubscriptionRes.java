package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SESActivateEsimSubscriptionRes extends CommonRes {

    @JsonProperty("iccid")
    private String iccid;
    @JsonProperty("activationCode")
    private String activationCode;
    @JsonProperty("imsi")
    private String imsi;
    @JsonProperty("msisdn")
    private String msisdn;
    @JsonProperty("activationStatus")
    private String activationStatus;
    @JsonProperty("iccidStatus")
    private String iccidStatus;
    //
    @JsonProperty("eid")
    private String eid;
    @JsonProperty("imei")
    private String imei;
    @JsonProperty("state")
    private String state;

    @JsonProperty("iccid")
    public String getIccid() {
        return iccid;
    }

    @JsonProperty("iccid")
    public void setIccid(String iccid) {this.iccid = iccid;}

    @JsonProperty("activationCode")
    public String getActivationCode() {
        return activationCode;
    }

    @JsonProperty("activationCode")
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
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

    @JsonProperty("eid")
    public String getEid() {
        return eid;
    }

    @JsonProperty("eid")
    public void setEid(String eid) {
        this.eid = eid;
    }

    @JsonProperty("imei")
    public String getImei() {
        return imei;
    }

    @JsonProperty("imei")
    public void setImei(String imei) {
        this.imei = imei;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SESActivateEsimSubscriptionRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("resultCode");
        sb.append('=');
        sb.append(((super.getResultCode() == null) ? "<null>" : super.getResultCode()));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((super.getDescription() == null) ? "<null>" : super.getDescription()));
        sb.append(',');
        sb.append("iccid");
        sb.append('=');
        sb.append(((this.iccid == null) ? "<null>" : this.iccid));
        sb.append(',');
        sb.append("activationCode");
        sb.append('=');
        sb.append(((this.activationCode == null) ? "<null>" : this.activationCode));
        sb.append(',');
        sb.append("imsi");
        sb.append('=');
        sb.append(((this.imsi == null) ? "<null>" : this.imsi));
        sb.append(',');
        sb.append("msisdn");
        sb.append('=');
        sb.append(((this.msisdn == null) ? "<null>" : this.msisdn));
        sb.append(',');
        sb.append("activationStatus");
        sb.append('=');
        sb.append(((this.activationStatus == null) ? "<null>" : this.activationStatus));
        sb.append(',');
        sb.append("iccidStatus");
        sb.append('=');
        sb.append(((this.iccidStatus == null) ? "<null>" : this.iccidStatus));
        sb.append(',');
        sb.append("eid");
        sb.append('=');
        sb.append(((this.eid == null) ? "<null>" : this.eid));
        sb.append(',');
        sb.append("imei");
        sb.append('=');
        sb.append(((this.imei == null) ? "<null>" : this.imei));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null) ? "<null>" : this.state));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
