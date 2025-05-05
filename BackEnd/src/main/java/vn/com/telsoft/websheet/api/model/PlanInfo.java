package vn.com.telsoft.websheet.api.model;

public class PlanInfo {

    private String planName;
    private Integer planType;
    private String operatorId;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PlanInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("planName");
        sb.append('=');
        sb.append(((this.planName == null) ? "<null>" : this.planName));
        sb.append(',');
        sb.append("planType");
        sb.append('=');
        sb.append(((this.planType == null) ? "<null>" : this.planType));
        sb.append(',');
        sb.append("operatorId");
        sb.append('=');
        sb.append(((this.operatorId == null) ? "<null>" : this.operatorId));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}