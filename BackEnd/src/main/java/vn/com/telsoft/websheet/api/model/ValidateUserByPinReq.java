package vn.com.telsoft.websheet.api.model;

public class ValidateUserByPinReq {

    private String msisdn;
    private String pin;

    public ValidateUserByPinReq() {

    }

    public ValidateUserByPinReq(String msisdn, String pin) {
        this.msisdn = msisdn;
        this.pin = pin;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ValidateUserByPinReq.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("msisdn");
        sb.append('=');
        sb.append(((this.msisdn == null) ? "<null>" : this.msisdn));
        sb.append(',');
        sb.append("pin");
        sb.append('=');
        sb.append(((this.pin == null) ? "<null>" : this.pin));
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
        result = ((result * 31) + ((this.msisdn == null) ? 0 : this.msisdn.hashCode()));
        result = ((result * 31) + ((this.pin == null) ? 0 : this.pin.hashCode()));
        return result;
    }
}
