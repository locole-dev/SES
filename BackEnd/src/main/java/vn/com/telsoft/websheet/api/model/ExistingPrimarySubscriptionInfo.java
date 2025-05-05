package vn.com.telsoft.websheet.api.model;

public class ExistingPrimarySubscriptionInfo {

    private String primaryMsisdn;
    private String primaryImsi;
    private String primaryImei;
    private String iccid;

    public String getPrimaryMsisdn() {
        return primaryMsisdn;
    }

    public void setPrimaryMsisdn(String primaryMsisdn) {
        this.primaryMsisdn = primaryMsisdn;
    }

    public String getPrimaryImsi() {
        return primaryImsi;
    }

    public void setPrimaryImsi(String primaryImsi) {
        this.primaryImsi = primaryImsi;
    }

    public String getPrimaryImei() {
        return primaryImei;
    }

    public void setPrimaryImei(String primaryImei) {
        this.primaryImei = primaryImei;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ExistingPrimarySubscriptionInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("primaryMsisdn");
        sb.append('=');
        sb.append(((this.primaryMsisdn == null) ? "<null>" : this.primaryMsisdn));
        sb.append(',');
        sb.append("primaryImsi");
        sb.append('=');
        sb.append(((this.primaryImsi == null) ? "<null>" : this.primaryImsi));
        sb.append(',');
        sb.append("primaryImei");
        sb.append('=');
        sb.append(((this.primaryImei == null) ? "<null>" : this.primaryImei));
        sb.append(',');
        sb.append("iccid");
        sb.append('=');
        sb.append(((this.iccid == null) ? "<null>" : this.iccid));
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
        result = ((result * 31) + ((this.primaryImsi == null) ? 0 : this.primaryImsi.hashCode()));
        result = ((result * 31) + ((this.iccid == null) ? 0 : this.iccid.hashCode()));
        result = ((result * 31) + ((this.primaryMsisdn == null) ? 0 : this.primaryMsisdn.hashCode()));
        result = ((result * 31) + ((this.primaryImei == null) ? 0 : this.primaryImei.hashCode()));
        return result;
    }

}