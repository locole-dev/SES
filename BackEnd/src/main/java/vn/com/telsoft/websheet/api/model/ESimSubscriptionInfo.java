package vn.com.telsoft.websheet.api.model;

public class ESimSubscriptionInfo {

    private String msisdn;
    private String imsi;
    private String newImsi;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getNewImsi() {
        return newImsi;
    }

    public void setNewImsi(String newImsi) {
        this.newImsi = newImsi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ESimSubscriptionInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("msisdn");
        sb.append('=');
        sb.append(((this.msisdn == null) ? "<null>" : this.msisdn));
        sb.append(',');
        sb.append("imsi");
        sb.append('=');
        sb.append(((this.imsi == null) ? "<null>" : this.imsi));
        sb.append(',');
        sb.append("newImsi");
        sb.append('=');
        sb.append(((this.newImsi == null) ? "<null>" : this.newImsi));
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
        result = ((result * 31) + ((this.imsi == null) ? 0 : this.imsi.hashCode()));
        result = ((result * 31) + ((this.msisdn == null) ? 0 : this.msisdn.hashCode()));
        result = ((result * 31) + ((this.newImsi == null) ? 0 : this.newImsi.hashCode()));
        return result;
    }
}