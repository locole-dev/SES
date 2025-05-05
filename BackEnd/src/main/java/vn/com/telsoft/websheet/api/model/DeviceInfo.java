package vn.com.telsoft.websheet.api.model;

public class DeviceInfo {

    private Integer deviceVendor;
    private String deviceType;
    private Integer deviceClassification;
    private String deviceModelName;
    private String deviceModelNumber;
    private String serialNumber;
    private Integer osType;
    private String osVersion;
    private String meid;
    private String displayName;
    private EuiccInfo euiccInfo;

    public Integer getDeviceVendor() {
        return deviceVendor;
    }

    public void setDeviceVendor(Integer deviceVendor) {
        this.deviceVendor = deviceVendor;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getDeviceClassification() {
        return deviceClassification;
    }

    public void setDeviceClassification(Integer deviceClassification) {
        this.deviceClassification = deviceClassification;
    }

    public String getDeviceModelName() {
        return deviceModelName;
    }

    public void setDeviceModelName(String deviceModelName) {
        this.deviceModelName = deviceModelName;
    }

    public String getDeviceModelNumber() {
        return deviceModelNumber;
    }

    public void setDeviceModelNumber(String deviceModelNumber) {
        this.deviceModelNumber = deviceModelNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public EuiccInfo getEuiccInfo() {
        return euiccInfo;
    }

    public void setEuiccInfo(EuiccInfo euiccInfo) {
        this.euiccInfo = euiccInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DeviceInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("deviceVendor");
        sb.append('=');
        sb.append(((this.deviceVendor == null) ? "<null>" : this.deviceVendor));
        sb.append(',');
        sb.append("deviceType");
        sb.append('=');
        sb.append(((this.deviceType == null) ? "<null>" : this.deviceType));
        sb.append(',');
        sb.append("deviceClassification");
        sb.append('=');
        sb.append(((this.deviceClassification == null) ? "<null>" : this.deviceClassification));
        sb.append(',');
        sb.append("deviceModelName");
        sb.append('=');
        sb.append(((this.deviceModelName == null) ? "<null>" : this.deviceModelName));
        sb.append(',');
        sb.append("deviceModelNumber");
        sb.append('=');
        sb.append(((this.deviceModelNumber == null) ? "<null>" : this.deviceModelNumber));
        sb.append(',');
        sb.append("serialNumber");
        sb.append('=');
        sb.append(((this.serialNumber == null) ? "<null>" : this.serialNumber));
        sb.append(',');
        sb.append("osType");
        sb.append('=');
        sb.append(((this.osType == null) ? "<null>" : this.osType));
        sb.append(',');
        sb.append("osVersion");
        sb.append('=');
        sb.append(((this.osVersion == null) ? "<null>" : this.osVersion));
        sb.append(',');
        sb.append("meid");
        sb.append('=');
        sb.append(((this.meid == null) ? "<null>" : this.meid));
        sb.append(',');
        sb.append("displayName");
        sb.append('=');
        sb.append(((this.displayName == null) ? "<null>" : this.displayName));
        sb.append(',');
        sb.append("euiccInfo");
        sb.append('=');
        sb.append(((this.euiccInfo == null) ? "<null>" : this.euiccInfo));
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
        result = ((result * 31) + ((this.deviceType == null) ? 0 : this.deviceType.hashCode()));
        result = ((result * 31) + ((this.meid == null) ? 0 : this.meid.hashCode()));
        result = ((result * 31) + ((this.serialNumber == null) ? 0 : this.serialNumber.hashCode()));
        result = ((result * 31) + ((this.osVersion == null) ? 0 : this.osVersion.hashCode()));
        result = ((result * 31) + ((this.deviceVendor == null) ? 0 : this.deviceVendor.hashCode()));
        result = ((result * 31) + ((this.displayName == null) ? 0 : this.displayName.hashCode()));
        result = ((result * 31) + ((this.deviceModelName == null) ? 0 : this.deviceModelName.hashCode()));
        result = ((result * 31) + ((this.osType == null) ? 0 : this.osType.hashCode()));
        result = ((result * 31) + ((this.deviceClassification == null) ? 0 : this.deviceClassification.hashCode()));
        result = ((result * 31) + ((this.deviceModelNumber == null) ? 0 : this.deviceModelNumber.hashCode()));
        result = ((result * 31) + ((this.euiccInfo == null) ? 0 : this.euiccInfo.hashCode()));
        return result;
    }

}