package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IMSI2MSISDNRes extends CommonRes{
    private String msisdn;

    @JsonProperty("isOverWritten")
    private Boolean isOverWritten;

    private String originalMsisdn;
    private Integer msisdnStatus;

    public IMSI2MSISDNRes() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Boolean getOverWritten() {
        return isOverWritten;
    }

    public void setOverWritten(Boolean isOverWritten) {
        this.isOverWritten = isOverWritten;
    }

    public String getOriginalMsisdn() {
        return originalMsisdn;
    }

    public void setOriginalMsisdn(String originalMsisdn) {
        this.originalMsisdn = originalMsisdn;
    }

    public Integer getMsisdnStatus() {
        return msisdnStatus;
    }

    public void setMsisdnStatus(Integer msisdnStatus) {
        this.msisdnStatus = msisdnStatus;
    }
}
