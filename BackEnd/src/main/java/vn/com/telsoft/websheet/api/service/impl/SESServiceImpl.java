package vn.com.telsoft.websheet.api.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import vn.com.telsoft.websheet.api.constants.Const;
import vn.com.telsoft.websheet.api.model.*;
import vn.com.telsoft.websheet.api.service.SESService;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SESServiceImpl implements SESService {
    private static final Logger logger = LoggerFactory.getLogger(SESService.class);

    private static final int CONNECT_TIMEOUT = 30000;
    private static final int SOCKET_TIMEOUT = 30000;

    @Value("${ses.ws.address}")
    private String wsAddress;
    @Value("${ses.ws.user}")
    private String wsUser;
    @Value("${ses.ws.pass}")
    private String wsPass;

    @Value("${ses.ws.address.authen}")
    private String wsAddress_authen;
    @Value("${ses.ws.address.activate}")
    private String wsAddress_activate;
    @Value("${ses.ws.address.manage}")
    private String wsAddress_manage;
    @Value("${ses.ws.address.recovery}")
    private String wsAddress_recovery;

    //Thêm cấu hình ses url

    @Value("${ses.url.validateUserByPin}")
    private String getUrlValidateUserByPin;
    @Value("${ses.url.activateEsimSubscription}")
    private String getUrlActivateEsimSubscription;
    @Value("${ses.url.eimSubscription}")
    private String getUrlEimSubscription;
    @Value("${ses.url.entitlements}")
    private String getUrlEntitlements;
    @Value("${ses.url.syncEsimSubInfo}")
    private String getUrlSyncEsimSubInfo;
    @Value("${ses.url.IMSI2MSISDN}")
    private String getUrlIMSI2MSISDN;
    @Value("${ses.url.eligibilityCheck}")
    private String getUrlEligibilityCheck;
    @Value("${ses.url.carrierPlan}")
    private String getUrlCarrierPlan;
    @Value("${ses.url.carrierPlan_IMSI_MSISDN}")
    private String getUrlCarrierPlan_IMSI_MSISDN;
    @Value("${ses.url.carrierPlan_PLAN_ID}")
    private String getUrlCarrierPlan_PLAN_ID;
    @Value("${ses.url.carrierPlan_eSIM}")
    private String getUrlCarrierPlan_eSIM;
    @Value("${ses.url.carrierPlan_5G}")
    private String getUrlCarrierPlan_5G;
    @Value("${ses.url.triggerSubscriptionActivation}")
    private String getUrlTriggerSubscriptionActivation;
    @Value("${ses.url.triggerSubscriptionRecovery}")
    private String getUrlTriggerSubscriptionRecovery;
    @Value("${ses.url.triggerSubscriptionDeactivation}")
    private String getUrlTriggerSubscriptionDeactivation;

    //

//    static final String URL_validateUserByPin = getUrlValidateUserByPin;
//    static final String URL_activateEsimSubscription = getUrlActivateEsimSubscription;
//    static final String URL_eimSubscription = getUrlEimSubscription;
//
//    static final String URL_entitlements = getUrlEntitlements;
//    static final String URL_syncEsimSubInfo = getUrlSyncEsimSubInfo;
//    static final String URL_IMSI2MSISDN = getUrlIMSI2MSISDN;
//    static final String URL_eligibilityCheck = getUrlEligibilityCheck;
//    //
//    static final String URL_carrierPlan = getUrlCarrierPlan;
//    static final String URL_carrierPlan_IMSI_MSISDN = getUrlCarrierPlan_IMSI_MSISDN;
//    static final String URL_carrierPlan_PLAN_ID = getUrlCarrierPlan_PLAN_ID;
//    static final String URL_carrierPlan_eSIM = getUrlCarrierPlan_eSIM;
//    static final String URL_carrierPlan_5G = getUrlCarrierPlan_5G;
//    //
//    static final String URL_triggerSubscriptionActivation = getUrlTriggerSubscriptionActivation;
//    static final String URL_triggerSubscriptionRecovery = getUrlTriggerSubscriptionRecovery;
//    static final String URL_triggerSubscriptionDeactivation = getUrlTriggerSubscriptionDeactivation;


    //Override timeouts in request factory
    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        //Timeout time with server connection: httpclient will create an asynchronous thread to create a socket connection, here set the connection timeout time of the socket
        clientHttpRequestFactory.setConnectTimeout(CONNECT_TIMEOUT);

        //socket read data timeout: timeout for getting response data from the server
        clientHttpRequestFactory.setReadTimeout(SOCKET_TIMEOUT);
        return clientHttpRequestFactory;
    }

    @Override
    public CommonRes validateUserByPin(ValidateUserByPinReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        //
//        headers.setBasicAuth(wsUser, wsPass, Charset.forName("UTF-8"));
//        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<ValidateUserByPinReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress_authen + getUrlValidateUserByPin, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                logger.info("Response body string:" + result.getBody());
                res = new CommonRes();

                if (result.getBody() != null && (result.getBody().contains("\"backend_error\"") || result.getBody().contains("\"error\""))) {
//                    !"".equals(result.getBody()) && !"{}".equals(result.getBody())
                    SESTriggerCommonResV2 errRes = objectMapper.readValue(result.getBody(), SESTriggerCommonResV2.class);
//                    int index = errRes.getMessage_vi().indexOf('-');
//                    String textBeforeDash = errRes.getMessage_vi().substring(0, index);
                    Pattern pattern = java.util.regex.Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(errRes.getError_number());
                    String numberAfterError = "";
                    if (matcher.find()) {
                        numberAfterError = matcher.group(); // Lấy số từ biểu thức chính quy
                    }
                    res.setTraceId(errRes.getTraceId());
                    res.setResultCode(numberAfterError);
                    res.setDescription(errRes.getError_description());
                } else {
                    res = new SESValidateUserByPinRes();
                    res.setResultCode(Const.COMMON_RESULT.SUCCESS);
                }
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
//            SESCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESCommonRes.class);
//            res = new CommonRes();
//            res.setResultCode(errRes.getError());
//            res.setDescription(errRes.getError_description());
            SESTriggerCommonResV2 errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESTriggerCommonResV2.class);
//            int index = errRes.getMessage_vi().indexOf('-');
//            String textBeforeDash = errRes.getMessage_vi().substring(0, index);
            Pattern pattern = java.util.regex.Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(errRes.getError_number());
            String numberAfterError = "";
            if (matcher.find()) {
                numberAfterError = matcher.group(); // Lấy số từ biểu thức chính quy
            }
            res = new CommonRes();
            res.setTraceId(errRes.getTraceId());
            res.setResultCode(numberAfterError);
            res.setDescription(errRes.getError_description());
        }
        return res;
    }

    @Override
    public CommonRes activateEsimSubscription(SESActivateEsimSubscriptionReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(wsUser, wsPass, Charset.forName("UTF-8"));
//        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set x-requestor-name
        headers.set("x-requestor-name", "eric-ses-sws");

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<SESActivateEsimSubscriptionReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress_activate + getUrlActivateEsimSubscription, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                logger.info("Response body string:" + result.getBody());
                res = objectMapper.readValue(result.getBody(), SESActivateEsimSubscriptionRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESTriggerCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESTriggerCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getErrorCode());
            res.setDescription(errRes.getErrorMsg());
        }
        return res;
    }

    @Override
    public CommonRes recoveryEsimSubscription(SESActivateEsimSubscriptionReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(wsUser, wsPass, Charset.forName("UTF-8"));
//        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set x-requestor-name
        headers.set("x-requestor-name", "eric-ses-sws");

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<SESActivateEsimSubscriptionReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress_recovery + getUrlActivateEsimSubscription, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                logger.info("Response body string:" + result.getBody());
                res = objectMapper.readValue(result.getBody(), SESActivateEsimSubscriptionRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESTriggerCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESTriggerCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getErrorCode());
            res.setDescription(errRes.getErrorMsg());
        }
        return res;
    }

    @Override
    public CommonRes esimSubscription(Map<String, String> params) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(wsUser, wsPass, Charset.forName("UTF-8"));
//        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity<String>: To get result as String.
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        //Converting of a hash map to a string of query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(wsAddress_manage + getUrlEimSubscription);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        try {
            // Gửi yêu cầu với phương thức GET, và các thông tin Headers.
            ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class, params);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                logger.info("Response body string:" + result.getBody());
                res = objectMapper.readValue(result.getBody(), EsimSubscriptionsRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESTriggerCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESTriggerCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getErrorCode());
            res.setDescription(errRes.getErrorMsg());
        }
        return res;
    }

    /*-------------------------------------------------------------------------------------------------------------------*/

    @Override
    public CommonRes triggerSubscriptionActivation(SubscriptionActivationReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(wsUser, wsPass, Charset.forName("UTF-8"));
        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<SubscriptionActivationReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress + getUrlTriggerSubscriptionActivation, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                res = objectMapper.readValue(result.getBody(), SubscriptionActivationRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESTriggerCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESTriggerCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getErrorCode());
            res.setDescription(errRes.getErrorMsg());
        }
        return res;
    }

    @Override
    public CommonRes triggerSubscriptionRecovery(SubscriptionRecoveryReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(wsUser, wsPass, Charset.forName("UTF-8"));
        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<SubscriptionRecoveryReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress + getUrlTriggerSubscriptionRecovery, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                res = objectMapper.readValue(result.getBody(), SubscriptionRecoveryRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESTriggerCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESTriggerCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getErrorCode());
            res.setDescription(errRes.getErrorMsg());
        }
        return res;
    }

    @Override
    public CommonRes triggerSubscriptionDeactivation(SubscriptionDeactivationReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(wsUser, wsPass, Charset.forName("UTF-8"));
        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<SubscriptionDeactivationReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress + getUrlTriggerSubscriptionDeactivation, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                res = objectMapper.readValue(result.getBody(), SubscriptionDeactivationRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESTriggerCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESTriggerCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getErrorCode());
            res.setDescription(errRes.getErrorMsg());
        }
        return res;
    }

    @Override
    public CommonRes carrierPlan(Map<String, String> params) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(wsUser, wsPass, Charset.forName("UTF-8"));
        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity<String>: To get result as String.
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        //Converting of a hash map to a string of query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(wsAddress + getUrlCarrierPlan);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        try {
            // Gửi yêu cầu với phương thức GET, và các thông tin Headers.
            ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity, String.class, params);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                res = objectMapper.readValue(result.getBody(), CarrierPlanRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getError());
            res.setDescription(errRes.getError_description());
        }
        return res;
    }

    @Override
    public CommonRes getEntitlement(GetEntitlementReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<GetEntitlementReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress + getUrlEntitlements, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                res = objectMapper.readValue(result.getBody(), GetEntitlementRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getError());
            res.setDescription(errRes.getError_description());
        }
        return res;
    }

    @Override
    public CommonRes syncEsimSubInfo(SyncEsimSubInfoReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<SyncEsimSubInfoReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress + getUrlSyncEsimSubInfo, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                res = objectMapper.readValue(result.getBody(), SyncEsimSubInfoRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getError());
            res.setDescription(errRes.getError_description());
        }
        return res;


    }

    @Override
    public CommonRes IMSI2MSISDN(String imsi) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity<String>: To get result as String.
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            // Gửi yêu cầu với phương thức GET, và các thông tin Headers.
            ResponseEntity<String> result = restTemplate.exchange(wsAddress + getUrlIMSI2MSISDN, HttpMethod.GET, requestEntity, String.class, imsi);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                res = objectMapper.readValue(result.getBody(), IMSI2MSISDNRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getError());
            res.setDescription(errRes.getError_description());
        }
        return res;
    }

    @Override
    public CommonRes eligibilityCheck(EligibilityCheckReq req) throws Exception {
        CommonRes res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(wsUser, wsPass);
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Dữ liệu đính kèm theo yêu cầu.
        HttpEntity<EligibilityCheckReq> requestBody = new HttpEntity<>(req, headers);

        try {
            // Gửi yêu cầu với phương thức POST.
            ResponseEntity<String> result = restTemplate.postForEntity(wsAddress + getUrlEligibilityCheck, requestBody, String.class);

            logger.info("Status code:" + result.getStatusCode());

            // Code = 200.
            if (result.getStatusCode() == HttpStatus.OK) {
                res = objectMapper.readValue(result.getBody(), EligibilityCheckRes.class);
                res.setResultCode(Const.COMMON_RESULT.SUCCESS);
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Code != 200.
            logger.info("Status code:" + ex.getRawStatusCode());
//            logger.info("ex.getResponseBodyAsString(): " + ex.getResponseBodyAsString());
            SESCommonRes errRes = objectMapper.readValue(ex.getResponseBodyAsString(), SESCommonRes.class);
            res = new CommonRes();
            res.setResultCode(errRes.getError());
            res.setDescription(errRes.getError_description());
        }
        return res;
    }

}
