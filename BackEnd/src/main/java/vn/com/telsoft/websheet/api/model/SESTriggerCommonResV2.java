package vn.com.telsoft.websheet.api.model;

public class SESTriggerCommonResV2 {

    private String traceId;
    private String message_vi;
    private String message_en;
    private String error_description;
    private String error_number;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getMessage_vi() {
        return message_vi;
    }

    public void setMessage_vi(String message_vi) {
        this.message_vi = message_vi;
    }

    public String getMessage_en() {
        return message_en;
    }

    public void setMessage_en(String message_en) {
        this.message_en = message_en;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public String getError_number() {
        return error_number;
    }

    public void setError_number(String error_number) {
        this.error_number = error_number;
    }

    public SESTriggerCommonResV2() {

    }
}
