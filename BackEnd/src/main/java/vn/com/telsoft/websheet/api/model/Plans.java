package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Plans {

    @JsonProperty("plan-category")
    private Integer planCategory;
    @JsonProperty("plan-group-options")
    private List<PlanGroupOption> planGroupOptions = null;

    public Integer getPlanCategory() {
        return planCategory;
    }

    public void setPlanCategory(Integer planCategory) {
        this.planCategory = planCategory;
    }

    public List<PlanGroupOption> getPlanGroupOptions() {
        return planGroupOptions;
    }

    public void setPlanGroupOptions(List<PlanGroupOption> planGroupOptions) {
        this.planGroupOptions = planGroupOptions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Plans.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("planCategory");
        sb.append('=');
        sb.append(((this.planCategory == null) ? "<null>" : this.planCategory));
        sb.append(',');
        sb.append("planGroupOptions");
        sb.append('=');
        sb.append(((this.planGroupOptions == null) ? "<null>" : this.planGroupOptions));
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
        result = ((result * 31) + ((this.planGroupOptions == null) ? 0 : this.planGroupOptions.hashCode()));
        result = ((result * 31) + ((this.planCategory == null) ? 0 : this.planCategory.hashCode()));
        return result;
    }

}