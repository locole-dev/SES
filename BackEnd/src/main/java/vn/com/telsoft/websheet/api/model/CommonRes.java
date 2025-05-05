package vn.com.telsoft.websheet.api.model;

public class CommonRes {

    private String traceId;
    private String resultCode;
    private String description;

    public CommonRes() {

    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommonRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("traceId");
        sb.append('=');
        sb.append(((this.traceId == null) ? "<null>" : this.traceId));
        sb.append(',');
        sb.append("resultCode");
        sb.append('=');
        sb.append(((this.resultCode == null) ? "<null>" : this.resultCode));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null) ? "<null>" : this.description));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
