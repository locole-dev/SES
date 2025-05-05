package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("entitlement-name")
    private String entitlementName;

    @JsonProperty("entitlement-status")
    private Integer entitlementStatus;

    @JsonProperty("error-description")
    private String errorDescription;

    public Response() {
    }

    public String getEntitlementName() {
        return entitlementName;
    }

    public void setEntitlementName(String entitlementName) {
        this.entitlementName = entitlementName;
    }

    public Integer getEntitlementStatus() {
        return entitlementStatus;
    }

    public void setEntitlementStatus(Integer entitlementStatus) {
        this.entitlementStatus = entitlementStatus;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
