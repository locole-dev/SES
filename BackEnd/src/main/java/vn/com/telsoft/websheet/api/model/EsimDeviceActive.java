package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EsimDeviceActive {

    @JsonProperty("eid")
    private String eid;
    @JsonProperty("device-name")
    private String deviceName;
    @JsonProperty("device-type")
    private String deviceType;
    @JsonProperty("serial-number")
    private String serialNumber;
    @JsonProperty("installed-iccids")
    private List<String> installedIccids = null;

    @JsonProperty("eid")
    public String getEid() {
        return eid;
    }

    @JsonProperty("eid")
    public void setEid(String eid) {
        this.eid = eid;
    }

    @JsonProperty("device-name")
    public String getDeviceName() {
        return deviceName;
    }

    @JsonProperty("device-name")
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @JsonProperty("device-type")
    public String getDeviceType() {
        return deviceType;
    }

    @JsonProperty("device-type")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @JsonProperty("serial-number")
    public String getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("serial-number")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @JsonProperty("installed-iccids")
    public List<String> getInstalledIccids() {
        return installedIccids;
    }

    @JsonProperty("installed-iccids")
    public void setInstalledIccids(List<String> installedIccids) {
        this.installedIccids = installedIccids;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EsimDeviceActive.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("eid");
        sb.append('=');
        sb.append(((this.eid == null) ? "<null>" : this.eid));
        sb.append(',');
        sb.append("deviceName");
        sb.append('=');
        sb.append(((this.deviceName == null) ? "<null>" : this.deviceName));
        sb.append(',');
        sb.append("deviceType");
        sb.append('=');
        sb.append(((this.deviceType == null) ? "<null>" : this.deviceType));
        sb.append(',');
        sb.append("serialNumber");
        sb.append('=');
        sb.append(((this.serialNumber == null) ? "<null>" : this.serialNumber));
        sb.append(',');
        sb.append("installedIccids");
        sb.append('=');
        sb.append(((this.installedIccids == null) ? "<null>" : this.installedIccids));
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
        result = ((result * 31) + ((this.eid == null) ? 0 : this.eid.hashCode()));
        result = ((result * 31) + ((this.deviceName == null) ? 0 : this.deviceName.hashCode()));
        result = ((result * 31) + ((this.deviceType == null) ? 0 : this.deviceType.hashCode()));
        result = ((result * 31) + ((this.serialNumber == null) ? 0 : this.serialNumber.hashCode()));
        result = ((result * 31) + ((this.installedIccids == null) ? 0 : this.installedIccids.hashCode()));
        return result;
    }

}