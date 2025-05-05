package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionDeactivationReq {

    @JsonProperty("unique-id")
    private String uniqueId;

    private SubscriberInfo subscriberInfo;
    private DeviceInfo deviceInfo;
    private SubscriptionInfo subscriptionInfo;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public SubscriberInfo getSubscriberInfo() {
        return subscriberInfo;
    }

    public void setSubscriberInfo(SubscriberInfo subscriberInfo) {
        this.subscriberInfo = subscriberInfo;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public void setSubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        this.subscriptionInfo = subscriptionInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SubscriptionDeactivationReq.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("uniqueId");
        sb.append('=');
        sb.append(((this.uniqueId == null) ? "<null>" : this.uniqueId));
        sb.append(',');
        sb.append("subscriberInfo");
        sb.append('=');
        sb.append(((this.subscriberInfo == null) ? "<null>" : this.subscriberInfo));
        sb.append(',');
        sb.append("deviceInfo");
        sb.append('=');
        sb.append(((this.deviceInfo == null) ? "<null>" : this.deviceInfo));
        sb.append(',');
        sb.append("subscriptionInfo");
        sb.append('=');
        sb.append(((this.subscriptionInfo == null) ? "<null>" : this.subscriptionInfo));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.subscriberInfo == null) ? 0 : this.subscriberInfo.hashCode()));
        result = ((result * 31) + ((this.uniqueId == null) ? 0 : this.uniqueId.hashCode()));
        result = ((result * 31) + ((this.deviceInfo == null) ? 0 : this.deviceInfo.hashCode()));
        result = ((result * 31) + ((this.subscriptionInfo == null) ? 0 : this.subscriptionInfo.hashCode()));
        return result;
    }

}