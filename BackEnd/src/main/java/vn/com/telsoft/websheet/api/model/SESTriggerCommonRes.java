package vn.com.telsoft.websheet.api.model;

public class SESTriggerCommonRes {

    private Integer statusCode;
    private String errorCode;
    private String errorMsg;

    public SESTriggerCommonRes() {

    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SESTriggerCommonRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("statusCode");
        sb.append('=');
        sb.append(((this.statusCode == null) ? "<null>" : this.statusCode));
        sb.append(',');
        sb.append("errorCode");
        sb.append('=');
        sb.append(((this.errorCode == null) ? "<null>" : this.errorCode));
        sb.append(',');
        sb.append("errorMsg");
        sb.append('=');
        sb.append(((this.errorMsg == null) ? "<null>" : this.errorMsg));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}


