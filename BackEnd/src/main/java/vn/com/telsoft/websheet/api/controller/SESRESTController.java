package vn.com.telsoft.websheet.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.com.telsoft.websheet.api.constants.Const;
import vn.com.telsoft.websheet.api.model.*;
import vn.com.telsoft.websheet.api.service.SESService;
import vn.com.telsoft.websheet.api.wdf.dto.*;
import vn.com.telsoft.websheet.api.wdf.service.CarrierMappingsService;
import vn.com.telsoft.websheet.api.wdf.service.DictionaryService;
import vn.com.telsoft.websheet.api.wdf.service.SmartWatchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class SESRESTController {
    public static final Logger logger = LoggerFactory.getLogger(SESRESTController.class);
    public static final Logger reporter = LoggerFactory.getLogger("reporter");

    @Autowired
    private SESService sesService;

    @Autowired
    private CarrierMappingsService carrierMappingsService;

    @Autowired
    private SmartWatchService smartWatchService;

    @Autowired
    private DictionaryService dictionaryService;

    // URL:
    // http(s)://{host:port}/{context}/validateUserByPin
    @RequestMapping(value = "/validateUserByPin", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes validateUserByPin(@RequestBody ValidateUserByPinReq req, HttpServletRequest request, HttpServletResponse response) {

        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        logger.info("SESRESTController validateUserByPin: ");

        CommonRes res = null;
        try {
            String prevURI = (String) request.getSession().getAttribute(Const.SESSION_KEY.PREV_URI);
            Map<String, String> mpToken = (Map<String, String>) request.getSession().getAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN);
            //
            response.setHeader(Const.SESSION_KEY.PREV_URI, prevURI);
            prevURI = prevURI.substring(prevURI.lastIndexOf("/") + 1);
            response.setHeader(Const.SESSION_KEY.REDIRECT_URI, prevURI);
            //
            req.setMsisdn(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.PRI_MSISDN));
            logger.info("Request body: " + req.toString());
            res = sesService.validateUserByPin(req);
            if (res != null && res.getResultCode().equals(Const.COMMON_RESULT.SUCCESS)) {
                //return deviceType for frontend
                ((SESValidateUserByPinRes) res).setDeviceType(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.DEVICE_TYPE));
            }
            logger.info("Response body json: " + res.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }

        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/activateEsimSubscription
    @RequestMapping(value = "/activateEsimSubscription", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes activateEsimSubscription(HttpServletRequest request) {

        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        logger.info("SESRESTController activateEsimSubscription: ");

        CommonRes res = null;
        try {
            Map<String, String> mpToken = (Map<String, String>) request.getSession().getAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN);

            SESActivateEsimSubscriptionReq req = new SESActivateEsimSubscriptionReq();
            req.setRequiredAction(1);//activate
            req.setEid(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.EID));
            req.setPrimaryImsi(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.PRI_IMSI));
            req.setPrimaryMsisdn(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.PRI_MSISDN));
            req.setDeviceType(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.DEVICE_TYPE));
            req.setActivationMode(Integer.parseInt(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.ACTIVATION_MODE)));
            /*The activation mode:
                - 0: The subscriber activates the eSIM device in standalone mode. The eSIM device has its own MSISDN.
                - 1: The subscriber activates the eSIM device in shared mode. The eSIM device shares the same MSISDN as the primary device.
                - 2: The subscriber activates a data only subscription. The phone number is not visible to the end user.*/
            req.setImei(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.IMEI));
            req.setDeviceId(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.DEVICE_ID));
            req.setApplicationCategory(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.APPLICATION_CATEGORY));
            //
            logger.info("Request body: " + req.toString());
            res = sesService.activateEsimSubscription(req);
            if (res != null && res.getResultCode().equals(Const.COMMON_RESULT.SUCCESS)) {
                //return for frontend
                ((SESActivateEsimSubscriptionRes) res).setEid(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.EID));
                ((SESActivateEsimSubscriptionRes) res).setImei(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.IMEI));
                ((SESActivateEsimSubscriptionRes) res).setState("active");
                reporter.info(((SESActivateEsimSubscriptionRes) res).getIccid() + " | " + ((SESActivateEsimSubscriptionRes) res).getActivationCode() + " | " + ((SESActivateEsimSubscriptionRes) res).getImsi() + " | " + ((SESActivateEsimSubscriptionRes) res).getActivationStatus() + " | " + ((SESActivateEsimSubscriptionRes) res).getIccidStatus());
            }
            logger.info("Response body json: " + res.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/manageEsimSubscription
    @RequestMapping(value = "/manageEsimSubscription", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes manageEsimSubscription(HttpServletRequest request) {

        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        logger.info("SESRESTController manageEsimSubscription: ");

        CommonRes res = null;
        try {
            Map<String, String> params = new HashMap<>();
            Map<String, String> mpToken = (Map<String, String>) request.getSession().getAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN);
            //set param
            params.put("primaryImsi", mpToken.get(Const.WEBSHEET_SESSION_TOKEN.PRI_IMSI));
            params.put("timestampRequired", "true");
            //
            logger.info("Request params: " + params.toString());
            res = sesService.esimSubscription(params);
            logger.info("Response body json: " + res.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/activateEsimSubscription
    @RequestMapping(value = "/recoveryEsimSubscription", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes recoveryEsimSubscription(HttpServletRequest request) {

        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        logger.info("SESRESTController recoveryEsimSubscription: ");

        CommonRes res = null;
        try {
            Map<String, String> mpToken = (Map<String, String>) request.getSession().getAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN);

            SESActivateEsimSubscriptionReq req = new SESActivateEsimSubscriptionReq();
            req.setRequiredAction(0);//recovery
            req.setEid(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.EID));
            req.setPrimaryImsi(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.PRI_IMSI));
            req.setPrimaryMsisdn(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.PRI_MSISDN));
            req.setDeviceType(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.DEVICE_TYPE));
            req.setActivationMode(Integer.parseInt(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.ACTIVATION_MODE)));
            /*The activation mode:
                - 0: The subscriber activates the eSIM device in standalone mode. The eSIM device has its own MSISDN.
                - 1: The subscriber activates the eSIM device in shared mode. The eSIM device shares the same MSISDN as the primary device.
                - 2: The subscriber activates a data only subscription. The phone number is not visible to the end user.*/
            req.setImei(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.IMEI));
            req.setDeviceId(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.DEVICE_ID));
            req.setApplicationCategory(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.APPLICATION_CATEGORY));
            if (res != null && res.getResultCode().equals(Const.COMMON_RESULT.SUCCESS)) {
                //return for frontend
                ((SESActivateEsimSubscriptionRes) res).setEid(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.EID));
                ((SESActivateEsimSubscriptionRes) res).setImei(mpToken.get(Const.WEBSHEET_SESSION_TOKEN.IMEI));
                ((SESActivateEsimSubscriptionRes) res).setState("active");
            }
            //
            logger.info("Request body: " + req.toString());
            res = sesService.recoveryEsimSubscription(req);
            logger.info("Response body json: " + res.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    /*------------------------------------------------------------------------------------------------------------------------------*/
    // URL:
    // http(s)://{host:port}/{context}/triggerSubscriptionActivation
    @RequestMapping(value = "/triggerSubscriptionActivation", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes triggerSubscriptionActivation(@RequestBody SubscriptionActivationReq req) {

        logger.info("SESRESTController triggerSubscriptionActivation: " + req.toString());

        CommonRes res = null;
        try {
            res = sesService.triggerSubscriptionActivation(req);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/triggerSubscriptionRecovery
    @RequestMapping(value = "/triggerSubscriptionRecovery", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes triggerSubscriptionRecovery(@RequestBody SubscriptionRecoveryReq req) {

        logger.info("SESRESTController triggerSubscriptionRecovery: " + req.toString());

        CommonRes res = null;
        try {
            res = sesService.triggerSubscriptionRecovery(req);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/triggerSubscriptionDeactivation
    @RequestMapping(value = "/triggerSubscriptionDeactivation", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes triggerSubscriptionDeactivation(@RequestBody SubscriptionDeactivationReq req) {

        logger.info("SESRESTController triggerSubscriptionDeactivation: " + req.toString());

        CommonRes res = null;
        try {
            res = sesService.triggerSubscriptionDeactivation(req);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/carrierPlan
    @RequestMapping(value = "/carrierPlan", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes carrierPlan(@RequestParam Map<String, String> params) {

        logger.info("SESRESTController carrierPlan: " + params.toString());

        CommonRes res = null;
        try {
            res = sesService.carrierPlan(params);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/entitlements
    @RequestMapping(value = "/entitlements", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes getEntitlement(@RequestBody GetEntitlementReq req) {

        logger.info("SESRESTController getEntitlement: " + req.toString());

        CommonRes res = null;
        try {
            res = sesService.getEntitlement(req);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/syncEsimSubInfo
    @RequestMapping(value = "/syncEsimSubInfo", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes syncEsimSubInfo(@RequestBody SyncEsimSubInfoReq req) {

        logger.info("SESRESTController syncEsimSubInfo: " + req.toString());

        CommonRes res = null;
        try {
            res = sesService.syncEsimSubInfo(req);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/msisdn?imsi={imsi}
    @RequestMapping(value = "/msisdn", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes imsi2msisdn(@RequestParam String imsi) {

        logger.info("SESRESTController imsi2msisdn: imsi = " + imsi);

        CommonRes res = null;
        try {
            res = sesService.IMSI2MSISDN(imsi);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    // URL:
    // http(s)://{host:port}/{context}/eligibilityCheck
    @RequestMapping(value = "/eligibilityCheck", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public CommonRes eligibilityCheck(@RequestBody EligibilityCheckReq req) {

        logger.info("SESRESTController eligibilityCheck: " + req.toString());

        CommonRes res = null;
        try {
            res = sesService.eligibilityCheck(req);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("ERROR: " + e.toString());
        }
        return res;
    }

    @RequestMapping(value = "/onInitData", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getTheme(HttpServletRequest request) {
        String carrier, smartWatch = null;
        if (request.getSession().getAttribute(Const.SESSION_KEY.WDF.CARRIER) != null && request.getSession().getAttribute(Const.SESSION_KEY.WDF.SMART_WATCH) != null) {
            carrier = request.getSession().getAttribute(Const.SESSION_KEY.WDF.CARRIER).toString();
            smartWatch = request.getSession().getAttribute(Const.SESSION_KEY.WDF.SMART_WATCH).toString();
            ThemeDTO theme = carrierMappingsService.findBySmartWatchNameAndCarrierName(smartWatch, carrier);
            if (theme == null) {
                theme = ThemeDTO.builder().status(0).style("default").logoUrl("default").build();
            }
            SmartWatchDTO smartWatchDTO = smartWatchService.findSmartWatchBySmartWatchName(smartWatch.toUpperCase());
            if (smartWatchDTO != null) {
                DictionaryDataDto dictionaryDataDto = dictionaryService.findDictionaryDataBySmartWatchKey(smartWatchDTO.getDictionaryKey());
                if (dictionaryDataDto == null) {
                    dictionaryDataDto = DictionaryDataDto.builder().eng(new HashMap<>()).vi(new HashMap<>()).build();
                }
                ThemeDTOV2 themeDTOV2 = new ThemeDTOV2();
                themeDTOV2.setTheme(theme);
                themeDTOV2.setDictionary(dictionaryDataDto);
                return ResponseEntity.status(HttpStatus.OK).body(themeDTOV2);
            } else{
                DictionaryDataDto dictionaryDataDto = DictionaryDataDto.builder().build();
                ThemeDTOV2 themeDTOV2 = new ThemeDTOV2();
                themeDTOV2.setTheme(theme);
                themeDTOV2.setDictionary(dictionaryDataDto);
                return ResponseEntity.status(HttpStatus.OK).body(themeDTOV2);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ThemeDTO.builder().status(0).style("default").logoUrl("default").build());
    }

}
