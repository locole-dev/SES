package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetEntitlementReq {
    private String imsi;

    @JsonProperty("user-id")
    private String userId;

    private String msisdn;

    @JsonProperty("device-category")
    private String deviceCategory;

    @JsonProperty("sim-type")
    private Integer simType;

    @JsonProperty("msisdn-category")
    private String msisdnCategory;

    @JsonProperty("operator-id")
    private String operatorId;

    @JsonProperty("entitlement-names")
    private List<String> entitlementNames;

    @JsonProperty("client-user-agent")
    private String clientUserAgent;

    public GetEntitlementReq() {
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public Integer getSimType() {
        return simType;
    }

    public void setSimType(Integer simType) {
        this.simType = simType;
    }

    public String getMsisdnCategory() {
        return msisdnCategory;
    }

    public void setMsisdnCategory(String msisdnCategory) {
        this.msisdnCategory = msisdnCategory;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public List<String> getEntitlementNames() {
        return entitlementNames;
    }

    public void setEntitlementNames(List<String> entitlementNames) {
        this.entitlementNames = entitlementNames;
    }

    public String getClientUserAgent() {
        return clientUserAgent;
    }

    public void setClientUserAgent(String clientUserAgent) {
        this.clientUserAgent = clientUserAgent;
    }

    @Override
    public String toString() {
        return "GetEntitlementRequest{" +
                "imsi='" + imsi + '\'' +
                ", userId='" + userId + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", deviceCategory='" + deviceCategory + '\'' +
                ", simType=" + simType +
                ", msisdnCategory='" + msisdnCategory + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", entitlementNames=" + entitlementNames +
                ", clientUserAgent='" + clientUserAgent + '\'' +
                '}';
    }
}
