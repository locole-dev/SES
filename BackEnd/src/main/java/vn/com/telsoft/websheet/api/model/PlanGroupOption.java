package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlanGroupOption {

    @JsonProperty("plan-id")
    private String planId;
    @JsonProperty("plan-type")
    private String planType;
    @JsonProperty("plan-label")
    private String planLabel;
    @JsonProperty("plan-description")
    private String planDescription;
    @JsonProperty("plan-value")
    private String planValue;
    @JsonProperty("plan-volume")
    private Integer planVolume;
    @JsonProperty("plan-subscribed")
    private String planSubscribed;
    @JsonProperty("plan-purchasable")
    private Boolean planPurchasable;
    @JsonProperty("plan-details-url")
    private String planDetailsUrl;
    @JsonProperty("plan-termcond-url")
    private String planTermcondUrl;
    @JsonProperty("plan-purchase-url")
    private String planPurchaseUrl;
    @JsonProperty("plan-supported-rat-list")
    private List<String> planSupportedRatList = null;
    @JsonProperty("plan-overrun-policy")
    private Integer planOverrunPolicy;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanLabel() {
        return planLabel;
    }

    public void setPlanLabel(String planLabel) {
        this.planLabel = planLabel;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public String getPlanValue() {
        return planValue;
    }

    public void setPlanValue(String planValue) {
        this.planValue = planValue;
    }

    public Integer getPlanVolume() {
        return planVolume;
    }

    public void setPlanVolume(Integer planVolume) {
        this.planVolume = planVolume;
    }

    public String getPlanSubscribed() {
        return planSubscribed;
    }

    public void setPlanSubscribed(String planSubscribed) {
        this.planSubscribed = planSubscribed;
    }

    public Boolean getPlanPurchasable() {
        return planPurchasable;
    }

    public void setPlanPurchasable(Boolean planPurchasable) {
        this.planPurchasable = planPurchasable;
    }

    public String getPlanDetailsUrl() {
        return planDetailsUrl;
    }

    public void setPlanDetailsUrl(String planDetailsUrl) {
        this.planDetailsUrl = planDetailsUrl;
    }

    public String getPlanTermcondUrl() {
        return planTermcondUrl;
    }

    public void setPlanTermcondUrl(String planTermcondUrl) {
        this.planTermcondUrl = planTermcondUrl;
    }

    public String getPlanPurchaseUrl() {
        return planPurchaseUrl;
    }

    public void setPlanPurchaseUrl(String planPurchaseUrl) {
        this.planPurchaseUrl = planPurchaseUrl;
    }

    public List<String> getPlanSupportedRatList() {
        return planSupportedRatList;
    }

    public void setPlanSupportedRatList(List<String> planSupportedRatList) {
        this.planSupportedRatList = planSupportedRatList;
    }

    public Integer getPlanOverrunPolicy() {
        return planOverrunPolicy;
    }

    public void setPlanOverrunPolicy(Integer planOverrunPolicy) {
        this.planOverrunPolicy = planOverrunPolicy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PlanGroupOption.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("planId");
        sb.append('=');
        sb.append(((this.planId == null) ? "<null>" : this.planId));
        sb.append(',');
        sb.append("planType");
        sb.append('=');
        sb.append(((this.planType == null) ? "<null>" : this.planType));
        sb.append(',');
        sb.append("planLabel");
        sb.append('=');
        sb.append(((this.planLabel == null) ? "<null>" : this.planLabel));
        sb.append(',');
        sb.append("planDescription");
        sb.append('=');
        sb.append(((this.planDescription == null) ? "<null>" : this.planDescription));
        sb.append(',');
        sb.append("planValue");
        sb.append('=');
        sb.append(((this.planValue == null) ? "<null>" : this.planValue));
        sb.append(',');
        sb.append("planVolume");
        sb.append('=');
        sb.append(((this.planVolume == null) ? "<null>" : this.planVolume));
        sb.append(',');
        sb.append("planSubscribed");
        sb.append('=');
        sb.append(((this.planSubscribed == null) ? "<null>" : this.planSubscribed));
        sb.append(',');
        sb.append("planPurchasable");
        sb.append('=');
        sb.append(((this.planPurchasable == null) ? "<null>" : this.planPurchasable));
        sb.append(',');
        sb.append("planDetailsUrl");
        sb.append('=');
        sb.append(((this.planDetailsUrl == null) ? "<null>" : this.planDetailsUrl));
        sb.append(',');
        sb.append("planTermcondUrl");
        sb.append('=');
        sb.append(((this.planTermcondUrl == null) ? "<null>" : this.planTermcondUrl));
        sb.append(',');
        sb.append("planPurchaseUrl");
        sb.append('=');
        sb.append(((this.planPurchaseUrl == null) ? "<null>" : this.planPurchaseUrl));
        sb.append(',');
        sb.append("planSupportedRatList");
        sb.append('=');
        sb.append(((this.planSupportedRatList == null) ? "<null>" : this.planSupportedRatList));
        sb.append(',');
        sb.append("planOverrunPolicy");
        sb.append('=');
        sb.append(((this.planOverrunPolicy == null) ? "<null>" : this.planOverrunPolicy));
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
        result = ((result * 31) + ((this.planValue == null) ? 0 : this.planValue.hashCode()));
        result = ((result * 31) + ((this.planSubscribed == null) ? 0 : this.planSubscribed.hashCode()));
        result = ((result * 31) + ((this.planType == null) ? 0 : this.planType.hashCode()));
        result = ((result * 31) + ((this.planPurchasable == null) ? 0 : this.planPurchasable.hashCode()));
        result = ((result * 31) + ((this.planLabel == null) ? 0 : this.planLabel.hashCode()));
        result = ((result * 31) + ((this.planPurchaseUrl == null) ? 0 : this.planPurchaseUrl.hashCode()));
        result = ((result * 31) + ((this.planDetailsUrl == null) ? 0 : this.planDetailsUrl.hashCode()));
        result = ((result * 31) + ((this.planId == null) ? 0 : this.planId.hashCode()));
        result = ((result * 31) + ((this.planDescription == null) ? 0 : this.planDescription.hashCode()));
        result = ((result * 31) + ((this.planVolume == null) ? 0 : this.planVolume.hashCode()));
        result = ((result * 31) + ((this.planTermcondUrl == null) ? 0 : this.planTermcondUrl.hashCode()));
        result = ((result * 31) + ((this.planSupportedRatList == null) ? 0 : this.planSupportedRatList.hashCode()));
        result = ((result * 31) + ((this.planOverrunPolicy == null) ? 0 : this.planOverrunPolicy.hashCode()));
        return result;
    }

}