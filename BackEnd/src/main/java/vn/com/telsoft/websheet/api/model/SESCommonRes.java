package vn.com.telsoft.websheet.api.model;

public class SESCommonRes {

    private String error;
    private String error_description;

    public SESCommonRes() {

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SESCommonRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("error");
        sb.append('=');
        sb.append(((this.error == null) ? "<null>" : this.error));
        sb.append(',');
        sb.append("error_description");
        sb.append('=');
        sb.append(((this.error_description == null) ? "<null>" : this.error_description));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
