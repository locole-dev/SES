package vn.com.telsoft.websheet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EsimSubscriptionsRes extends CommonRes {

    @JsonProperty("esimSubscriptions")
    private List<EsimSubscription> esimSubscriptions = null;

    @JsonProperty("esimSubscriptions")
    public List<EsimSubscription> getEsimSubscriptions() {
        return esimSubscriptions;
    }

    @JsonProperty("esimSubscriptions")
    public void setEsimSubscriptions(List<EsimSubscription> esimSubscriptions) {
        this.esimSubscriptions = esimSubscriptions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(EsimSubscriptionsRes.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("resultCode");
        sb.append('=');
        sb.append(((super.getResultCode() == null) ? "<null>" : super.getResultCode()));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((super.getDescription() == null) ? "<null>" : super.getDescription()));
        sb.append(',');
        sb.append("esimSubscriptions");
        sb.append('=');
        sb.append(((this.esimSubscriptions == null) ? "<null>" : this.esimSubscriptions));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}