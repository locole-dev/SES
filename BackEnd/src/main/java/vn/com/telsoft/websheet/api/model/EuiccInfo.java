package vn.com.telsoft.websheet.api.model;

public class EuiccInfo {

    private String imei;
    private String eid;
    private String iccid;
    private String simAllianceProfileVersion;
    private String gsmaSgp22Version;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getSimAllianceProfileVersion() {
        return simAllianceProfileVersion;
    }

    public void setSimAllianceProfileVersion(String simAllianceProfileVersion) {
        this.simAllianceProfileVersion = simAllianceProfileVersion;
    }

    public String getGsmaSgp22Version() {
        return gsmaSgp22Version;
    }

    public void setGsmaSgp22Version(String gsmaSgp22Version) {
        this.gsmaSgp22Version = gsmaSgp22Version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EuiccInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("imei");
        sb.append('=');
        sb.append(((this.imei == null) ? "<null>" : this.imei));
        sb.append(',');
        sb.append("eid");
        sb.append('=');
        sb.append(((this.eid == null) ? "<null>" : this.eid));
        sb.append(',');
        sb.append("iccid");
        sb.append('=');
        sb.append(((this.iccid == null) ? "<null>" : this.iccid));
        sb.append(',');
        sb.append("simAllianceProfileVersion");
        sb.append('=');
        sb.append(((this.simAllianceProfileVersion == null) ? "<null>" : this.simAllianceProfileVersion));
        sb.append(',');
        sb.append("gsmaSgp22Version");
        sb.append('=');
        sb.append(((this.gsmaSgp22Version == null) ? "<null>" : this.gsmaSgp22Version));
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
        result = ((result * 31) + ((this.imei == null) ? 0 : this.imei.hashCode()));
        result = ((result * 31) + ((this.eid == null) ? 0 : this.eid.hashCode()));
        result = ((result * 31) + ((this.iccid == null) ? 0 : this.iccid.hashCode()));
        result = ((result * 31) + ((this.simAllianceProfileVersion == null) ? 0 : this.simAllianceProfileVersion.hashCode()));
        result = ((result * 31) + ((this.gsmaSgp22Version == null) ? 0 : this.gsmaSgp22Version.hashCode()));
        return result;
    }
}