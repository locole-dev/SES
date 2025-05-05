package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SESValidateUserByPinRes extends CommonRes {

    @JsonProperty("deviceType")
    private String deviceType;

    @JsonProperty("deviceType")
    public String getDeviceType() {
        return deviceType;
    }

    @JsonProperty("deviceType")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SESValidateUserByPinRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("resultCode");
        sb.append('=');
        sb.append(((super.getResultCode() == null) ? "<null>" : super.getResultCode()));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((super.getDescription() == null) ? "<null>" : super.getDescription()));
        sb.append(',');
        sb.append("deviceType");
        sb.append('=');
        sb.append(((this.deviceType == null) ? "<null>" : this.deviceType));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}