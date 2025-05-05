package vn.com.telsoft.websheet.api.model;

public class SubscriptionInfo {

    private Integer subscriptionType;
    private Integer numberPlanType;
    private ESimSubscriptionInfo eSimSubscriptionInfo;
    private ExistingPrimarySubscriptionInfo existingPrimarySubscriptionInfo;

    public Integer getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(Integer subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Integer getNumberPlanType() {
        return numberPlanType;
    }

    public void setNumberPlanType(Integer numberPlanType) {
        this.numberPlanType = numberPlanType;
    }

    public ESimSubscriptionInfo geteSimSubscriptionInfo() {
        return eSimSubscriptionInfo;
    }

    public void seteSimSubscriptionInfo(ESimSubscriptionInfo eSimSubscriptionInfo) {
        this.eSimSubscriptionInfo = eSimSubscriptionInfo;
    }

    public ExistingPrimarySubscriptionInfo getExistingPrimarySubscriptionInfo() {
        return existingPrimarySubscriptionInfo;
    }

    public void setExistingPrimarySubscriptionInfo(ExistingPrimarySubscriptionInfo existingPrimarySubscriptionInfo) {
        this.existingPrimarySubscriptionInfo = existingPrimarySubscriptionInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SubscriptionInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("subscriptionType");
        sb.append('=');
        sb.append(((this.subscriptionType == null) ? "<null>" : this.subscriptionType));
        sb.append(',');
        sb.append("numberPlanType");
        sb.append('=');
        sb.append(((this.numberPlanType == null) ? "<null>" : this.numberPlanType));
        sb.append(',');
        sb.append("eSimSubscriptionInfo");
        sb.append('=');
        sb.append(((this.eSimSubscriptionInfo == null) ? "<null>" : this.eSimSubscriptionInfo));
        sb.append(',');
        sb.append("existingPrimarySubscriptionInfo");
        sb.append('=');
        sb.append(((this.existingPrimarySubscriptionInfo == null) ? "<null>" : this.existingPrimarySubscriptionInfo));
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
        result = ((result * 31) + ((this.numberPlanType == null) ? 0 : this.numberPlanType.hashCode()));
        result = ((result * 31) + ((this.subscriptionType == null) ? 0 : this.subscriptionType.hashCode()));
        result = ((result * 31) + ((this.eSimSubscriptionInfo == null) ? 0 : this.eSimSubscriptionInfo.hashCode()));
        result = ((result * 31) + ((this.existingPrimarySubscriptionInfo == null) ? 0 : this.existingPrimarySubscriptionInfo.hashCode()));
        return result;
    }

}