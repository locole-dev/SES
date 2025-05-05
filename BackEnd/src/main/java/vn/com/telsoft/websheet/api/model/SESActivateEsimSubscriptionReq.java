package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SESActivateEsimSubscriptionReq {

    @JsonProperty("required-action")
    private Integer requiredAction;
    @JsonProperty("eid")
    private String eid;
    @JsonProperty("primary-imsi")
    private String primaryImsi;
    @JsonProperty("primary-msisdn")
    private String primaryMsisdn;
    @JsonProperty("device-type")
    private String deviceType;
    @JsonProperty("activation-mode")
    private Integer activationMode;
    @JsonProperty("imei")
    private String imei;
    @JsonProperty("device-id")
    private String deviceId;
    @JsonProperty("application-category")
    private String applicationCategory;

    @JsonProperty("required-action")
    public Integer getRequiredAction() {
        return requiredAction;
    }

    @JsonProperty("required-action")
    public void setRequiredAction(Integer requiredAction) {
        this.requiredAction = requiredAction;
    }

    @JsonProperty("eid")
    public String getEid() {
        return eid;
    }

    @JsonProperty("eid")
    public void setEid(String eid) {
        this.eid = eid;
    }

    @JsonProperty("primary-imsi")
    public String getPrimaryImsi() {
        return primaryImsi;
    }

    @JsonProperty("primary-imsi")
    public void setPrimaryImsi(String primaryImsi) {
        this.primaryImsi = primaryImsi;
    }

    @JsonProperty("primary-msisdn")
    public String getPrimaryMsisdn() {
        return primaryMsisdn;
    }

    @JsonProperty("primary-msisdn")
    public void setPrimaryMsisdn(String primaryMsisdn) {
        this.primaryMsisdn = primaryMsisdn;
    }

    @JsonProperty("device-type")
    public String getDeviceType() {
        return deviceType;
    }

    @JsonProperty("device-type")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @JsonProperty("activation-mode")
    public Integer getActivationMode() {
        return activationMode;
    }

    @JsonProperty("activation-mode")
    public void setActivationMode(Integer activationMode) {
        this.activationMode = activationMode;
    }

    @JsonProperty("imei")
    public String getImei() {
        return imei;
    }

    @JsonProperty("imei")
    public void setImei(String imei) {
        this.imei = imei;
    }

    @JsonProperty("device-id")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty("device-id")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("application-category")
    public String getApplicationCategory() {
        return applicationCategory;
    }

    @JsonProperty("application-category")
    public void setApplicationCategory(String applicationCategory) {
        this.applicationCategory = applicationCategory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SESActivateEsimSubscriptionReq.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("required-action");
        sb.append('=');
        sb.append(((this.requiredAction == null) ? "<null>" : this.requiredAction));
        sb.append(',');
        sb.append("eid");
        sb.append('=');
        sb.append(((this.eid == null) ? "<null>" : this.eid));
        sb.append(',');
        sb.append("primaryImsi");
        sb.append('=');
        sb.append(((this.primaryImsi == null) ? "<null>" : this.primaryImsi));
        sb.append(',');
        sb.append("primaryMsisdn");
        sb.append('=');
        sb.append(((this.primaryMsisdn == null) ? "<null>" : this.primaryMsisdn));
        sb.append(',');
        sb.append("deviceType");
        sb.append('=');
        sb.append(((this.deviceType == null) ? "<null>" : this.deviceType));
        sb.append(',');
        sb.append("activationMode");
        sb.append('=');
        sb.append(((this.activationMode == null) ? "<null>" : this.activationMode));
        sb.append(',');
        sb.append("imei");
        sb.append('=');
        sb.append(((this.imei == null) ? "<null>" : this.imei));
        sb.append(',');
        sb.append("deviceId");
        sb.append('=');
        sb.append(((this.deviceId == null) ? "<null>" : this.deviceId));
        sb.append(',');
        sb.append("applicationCategory");
        sb.append('=');
        sb.append(((this.applicationCategory == null) ? "<null>" : this.applicationCategory));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}