package vn.com.telsoft.websheet.api.model;


public class IccidInfo {

    private String iccid;
    private String activationCode;
    private String confirmationCode;
    private String smdpAddress;
    private Boolean useSmds;
    private String iccidStatus;
    private Integer delayedProfileDownloadTime;

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public String getSmdpAddress() {
        return smdpAddress;
    }

    public void setSmdpAddress(String smdpAddress) {
        this.smdpAddress = smdpAddress;
    }

    public Boolean getUseSmds() {
        return useSmds;
    }

    public void setUseSmds(Boolean useSmds) {
        this.useSmds = useSmds;
    }

    public String getIccidStatus() {
        return iccidStatus;
    }

    public void setIccidStatus(String iccidStatus) {
        this.iccidStatus = iccidStatus;
    }

    public Integer getDelayedProfileDownloadTime() {
        return delayedProfileDownloadTime;
    }

    public void setDelayedProfileDownloadTime(Integer delayedProfileDownloadTime) {
        this.delayedProfileDownloadTime = delayedProfileDownloadTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(IccidInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("iccid");
        sb.append('=');
        sb.append(((this.iccid == null) ? "<null>" : this.iccid));
        sb.append(',');
        sb.append("activationCode");
        sb.append('=');
        sb.append(((this.activationCode == null) ? "<null>" : this.activationCode));
        sb.append(',');
        sb.append("confirmationCode");
        sb.append('=');
        sb.append(((this.confirmationCode == null) ? "<null>" : this.confirmationCode));
        sb.append(',');
        sb.append("smdpAddress");
        sb.append('=');
        sb.append(((this.smdpAddress == null) ? "<null>" : this.smdpAddress));
        sb.append(',');
        sb.append("useSmds");
        sb.append('=');
        sb.append(((this.useSmds == null) ? "<null>" : this.useSmds));
        sb.append(',');
        sb.append("iccidStatus");
        sb.append('=');
        sb.append(((this.iccidStatus == null) ? "<null>" : this.iccidStatus));
        sb.append(',');
        sb.append("delayedProfileDownloadTime");
        sb.append('=');
        sb.append(((this.delayedProfileDownloadTime == null) ? "<null>" : this.delayedProfileDownloadTime));
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
        result = ((result * 31) + ((this.confirmationCode == null) ? 0 : this.confirmationCode.hashCode()));
        result = ((result * 31) + ((this.iccid == null) ? 0 : this.iccid.hashCode()));
        result = ((result * 31) + ((this.delayedProfileDownloadTime == null) ? 0 : this.delayedProfileDownloadTime.hashCode()));
        result = ((result * 31) + ((this.activationCode == null) ? 0 : this.activationCode.hashCode()));
        result = ((result * 31) + ((this.useSmds == null) ? 0 : this.useSmds.hashCode()));
        result = ((result * 31) + ((this.smdpAddress == null) ? 0 : this.smdpAddress.hashCode()));
        result = ((result * 31) + ((this.iccidStatus == null) ? 0 : this.iccidStatus.hashCode()));
        return result;
    }

}