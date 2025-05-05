package vn.com.telsoft.websheet.api.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionRecoveryRes extends CommonRes {

    @JsonProperty("unique-id")
    private String uniqueId;

    private ServiceInfo serviceInfo;
    private IccidInfo iccidInfo;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public IccidInfo getIccidInfo() {
        return iccidInfo;
    }

    public void setIccidInfo(IccidInfo iccidInfo) {
        this.iccidInfo = iccidInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SubscriptionRecoveryRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("uniqueId");
        sb.append('=');
        sb.append(((this.uniqueId == null) ? "<null>" : this.uniqueId));
        sb.append(',');
        sb.append("serviceInfo");
        sb.append('=');
        sb.append(((this.serviceInfo == null) ? "<null>" : this.serviceInfo));
        sb.append(',');
        sb.append("iccidInfo");
        sb.append('=');
        sb.append(((this.iccidInfo == null) ? "<null>" : this.iccidInfo));
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
        result = ((result * 31) + ((this.serviceInfo == null) ? 0 : this.serviceInfo.hashCode()));
        result = ((result * 31) + ((this.iccidInfo == null) ? 0 : this.iccidInfo.hashCode()));
        result = ((result * 31) + ((this.uniqueId == null) ? 0 : this.uniqueId.hashCode()));
        return result;
    }

}