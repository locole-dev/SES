package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EligibilityCheckRes extends CommonRes {

    @JsonProperty("result-code")
    private Integer codeResult;

    public EligibilityCheckRes() {
    }

    public Integer getCodeResult() {
        return codeResult;
    }

    public void setCodeResult(Integer codeResult) {
        this.codeResult = codeResult;
    }

    @Override
    public String toString() {
        return "EligibilityCheckResponse{" +
                "codeResult=" + codeResult +
                '}';
    }
}
