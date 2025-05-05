package vn.com.telsoft.websheet.api.model;

import java.util.List;

public class SyncEsimSubInfoRes extends CommonRes {

    private List<ESimSubscriptionList> eSimSubscriptionList;
//    private CommonRes commonRes;

    public SyncEsimSubInfoRes() {
    }

    public List<ESimSubscriptionList> geteSimSubscriptionList() {
        return eSimSubscriptionList;
    }

    public void seteSimSubscriptionList(List<ESimSubscriptionList> eSimSubscriptionList) {
        this.eSimSubscriptionList = eSimSubscriptionList;
    }

    @Override
    public String toString() {
        return "SyncEsimSubInfoResponse{" +
                "eSimSubscriptionList=" + eSimSubscriptionList +
                '}';
    }
}
