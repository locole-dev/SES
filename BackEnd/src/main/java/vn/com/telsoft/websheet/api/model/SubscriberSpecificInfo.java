package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriberSpecificInfo {
    private String language;

    @JsonProperty("operator-brand")
    private String operatorBrand;

    @JsonProperty("other-info")
    private Object otherInfo;

    public SubscriberSpecificInfo() {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOperatorBrand() {
        return operatorBrand;
    }

    public void setOperatorBrand(String operatorBrand) {
        this.operatorBrand = operatorBrand;
    }

    public Object getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Object otherInfo) {
        this.otherInfo = otherInfo;
    }
}
