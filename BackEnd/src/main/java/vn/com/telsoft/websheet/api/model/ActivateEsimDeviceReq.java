package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivateEsimDeviceReq {

    @JsonProperty("websheet-session-token")
    private String websheetSessionToken;
    @JsonProperty("esim-device-active")
    private EsimDeviceActive esimDeviceActive;

    //iOS
    @JsonProperty("carrierPostData")
    private String carrierPostData;

    //get-set
    @JsonProperty("websheet-session-token")
    public String getWebsheetSessionToken() {
        return websheetSessionToken;
    }

    @JsonProperty("websheet-session-token")
    public void setWebsheetSessionToken(String websheetSessionToken) {
        this.websheetSessionToken = websheetSessionToken;
    }

    @JsonProperty("esim-device-active")
    public EsimDeviceActive getEsimDeviceActive() {
        return esimDeviceActive;
    }

    @JsonProperty("esim-device-active")
    public void setEsimDeviceActive(EsimDeviceActive esimDeviceActive) {
        this.esimDeviceActive = esimDeviceActive;
    }

    //iOS
    @JsonProperty("carrierPostData")
    public String getCarrierPostData() {
        return carrierPostData;
    }

    @JsonProperty("carrierPostData")
    public void setCarrierPostData(String carrierPostData) {
        this.carrierPostData = carrierPostData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ActivateEsimDeviceReq.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("websheetSessionToken");
        sb.append('=');
        sb.append(((this.websheetSessionToken == null) ? "<null>" : this.websheetSessionToken));
        sb.append(',');
        sb.append("carrierPostData");
        sb.append('=');
        sb.append(((this.carrierPostData == null) ? "<null>" : this.carrierPostData));
        sb.append(',');
        sb.append("esimDeviceActive");
        sb.append('=');
        sb.append(((this.esimDeviceActive == null) ? "<null>" : this.esimDeviceActive));
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
        result = ((result * 31) + ((this.websheetSessionToken == null) ? 0 : this.websheetSessionToken.hashCode()));
        result = ((result * 31) + ((this.carrierPostData == null) ? 0 : this.carrierPostData.hashCode()));
        result = ((result * 31) + ((this.esimDeviceActive == null) ? 0 : this.esimDeviceActive.hashCode()));
        return result;
    }

}