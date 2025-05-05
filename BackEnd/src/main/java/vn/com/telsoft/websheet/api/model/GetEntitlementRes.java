package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetEntitlementRes extends CommonRes {
    private Integer status;

    @JsonProperty("subscription-id")
    private List<String> subscriptionId;

    private List<Response> response;

    @JsonProperty("subscriber-specific-info")
    private SubscriberSpecificInfo subscriberSpecificInfo;

    public GetEntitlementRes() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(List<String> subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public SubscriberSpecificInfo getSubscriberSpecificInfo() {
        return subscriberSpecificInfo;
    }

    public void setSubscriberSpecificInfo(SubscriberSpecificInfo subscriberSpecificInfo) {
        this.subscriberSpecificInfo = subscriberSpecificInfo;
    }

    @Override
    public String toString() {
        return "GetEntitlementResponse{" +
                "status=" + status +
                ", subscriptionId=" + subscriptionId +
                ", response=" + response +
                ", subscriberSpecificInfo=" + subscriberSpecificInfo +
                '}';
    }
}
