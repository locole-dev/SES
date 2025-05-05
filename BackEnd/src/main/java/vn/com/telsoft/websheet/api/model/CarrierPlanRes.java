package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CarrierPlanRes extends CommonRes {

    @JsonProperty("plans-list")
    private List<Plans> plansList = null;
    @JsonProperty("more-plans-url")
    private String morePlansUrl;

    public List<Plans> getPlansList() {
        return plansList;
    }

    public void setPlansList(List<Plans> plansList) {
        this.plansList = plansList;
    }

    public String getMorePlansUrl() {
        return morePlansUrl;
    }

    public void setMorePlansUrl(String morePlansUrl) {
        this.morePlansUrl = morePlansUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CarrierPlanRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("plansList");
        sb.append('=');
        sb.append(((this.plansList == null) ? "<null>" : this.plansList));
        sb.append(',');
        sb.append("morePlansUrl");
        sb.append('=');
        sb.append(((this.morePlansUrl == null) ? "<null>" : this.morePlansUrl));
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
        result = ((result * 31) + ((this.plansList == null) ? 0 : this.plansList.hashCode()));
        result = ((result * 31) + ((this.morePlansUrl == null) ? 0 : this.morePlansUrl.hashCode()));
        return result;
    }

}