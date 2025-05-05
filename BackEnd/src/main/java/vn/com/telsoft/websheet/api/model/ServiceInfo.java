package vn.com.telsoft.websheet.api.model;


public class ServiceInfo {

    private String imsi;
    private String msisdn;
    private String subscriptionStatus;
    private Integer provisionStatus;
    private Integer networkRegistrationInterval;

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public Integer getProvisionStatus() {
        return provisionStatus;
    }

    public void setProvisionStatus(Integer provisionStatus) {
        this.provisionStatus = provisionStatus;
    }

    public Integer getNetworkRegistrationInterval() {
        return networkRegistrationInterval;
    }

    public void setNetworkRegistrationInterval(Integer networkRegistrationInterval) {
        this.networkRegistrationInterval = networkRegistrationInterval;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ServiceInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("imsi");
        sb.append('=');
        sb.append(((this.imsi == null) ? "<null>" : this.imsi));
        sb.append(',');
        sb.append("msisdn");
        sb.append('=');
        sb.append(((this.msisdn == null) ? "<null>" : this.msisdn));
        sb.append(',');
        sb.append("subscriptionStatus");
        sb.append('=');
        sb.append(((this.subscriptionStatus == null) ? "<null>" : this.subscriptionStatus));
        sb.append(',');
        sb.append("provisionStatus");
        sb.append('=');
        sb.append(((this.provisionStatus == null) ? "<null>" : this.provisionStatus));
        sb.append(',');
        sb.append("networkRegistrationInterval");
        sb.append('=');
        sb.append(((this.networkRegistrationInterval == null) ? "<null>" : this.networkRegistrationInterval));
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
        result = ((result * 31) + ((this.subscriptionStatus == null) ? 0 : this.subscriptionStatus.hashCode()));
        result = ((result * 31) + ((this.networkRegistrationInterval == null) ? 0 : this.networkRegistrationInterval.hashCode()));
        result = ((result * 31) + ((this.imsi == null) ? 0 : this.imsi.hashCode()));
        result = ((result * 31) + ((this.msisdn == null) ? 0 : this.msisdn.hashCode()));
        result = ((result * 31) + ((this.provisionStatus == null) ? 0 : this.provisionStatus.hashCode()));
        return result;
    }

}