package vn.com.telsoft.websheet.api.model;

public class SubscriberInfo {

    private String subscriberId;
    private String billingAccountNumber;
    private String emailAddress;
    private String contactPhoneNumber;

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    public void setBillingAccountNumber(String billingAccountNumber) {
        this.billingAccountNumber = billingAccountNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SubscriberInfo.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("subscriberId");
        sb.append('=');
        sb.append(((this.subscriberId == null) ? "<null>" : this.subscriberId));
        sb.append(',');
        sb.append("billingAccountNumber");
        sb.append('=');
        sb.append(((this.billingAccountNumber == null) ? "<null>" : this.billingAccountNumber));
        sb.append(',');
        sb.append("emailAddress");
        sb.append('=');
        sb.append(((this.emailAddress == null) ? "<null>" : this.emailAddress));
        sb.append(',');
        sb.append("contactPhoneNumber");
        sb.append('=');
        sb.append(((this.contactPhoneNumber == null) ? "<null>" : this.contactPhoneNumber));
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
        result = ((result * 31) + ((this.subscriberId == null) ? 0 : this.subscriberId.hashCode()));
        result = ((result * 31) + ((this.emailAddress == null) ? 0 : this.emailAddress.hashCode()));
        result = ((result * 31) + ((this.contactPhoneNumber == null) ? 0 : this.contactPhoneNumber.hashCode()));
        result = ((result * 31) + ((this.billingAccountNumber == null) ? 0 : this.billingAccountNumber.hashCode()));
        return result;
    }
}