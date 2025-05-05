package vn.com.telsoft.websheet.api.constants;

public class Const {

    public static final class SESSION_KEY {
        public static final String IS_AUTHORIZED = "isAuthorized";
        public static final String PREV_URI = "prevURI";
        public static final String REDIRECT_URI = "redirectURI";
        public static final String WEBSHEET_SESSION_TOKEN = "websheet-session-token";
        public static final String MSISDN = "msisdn";
        public static final String ERR_CODE = "errorCode";
        public static final String ERR_MSG = "errorMsg";

        public static final class WDF {
            public static final String CARRIER = "carrier";
            public static final String SMART_WATCH = "smart-watch";
        }
    }

    public static final class WEBSHEET_SESSION_TOKEN {
        public static final String EID = "eid";
        public static final String PRI_IMSI = "primary-imsi";
        public static final String PRI_MSISDN = "primary-msisdn";
        public static final String PRI_IMEI = "primary-imei";
        public static final String IMEI = "imei";
        public static final String DEVICE_ID = "device-id";
        public static final String DEVICE_TYPE = "device-type";
        public static final String ACTIVATION_MODE = "activation-mode";
        public static final String APPLICATION_CATEGORY = "application-category";
        public static final String IS_FACTORY = "is-factory";
    }

    public static final class COMMON_RESULT {
        public static final String SUCCESS = "0";
    }


}
