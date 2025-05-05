package vn.com.telsoft.websheet.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.telsoft.websheet.api.model.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/ses")
public class SESRESTEmulatorController {
    public static final Logger logger = LoggerFactory.getLogger(SESRESTEmulatorController.class);
    public static final Logger reporter = LoggerFactory.getLogger("reporter");

    // URL:
    // http://localhost:8080/SomeContextPath/validateUserByPin
    @RequestMapping(value = "/authservice/v1/validateUserByPin", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity validateUserByPin(@RequestBody ValidateUserByPinReq req) throws IOException {
        logger.info("SESRESTEmulatorController validateUserByPin: " + req.toString());
        SESTriggerCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        String strErr = "{}";
        //
        if (req == null || req.getMsisdn() == null || req.getPin() == null) {
            strErr = "{\n" +
                    "  \"errorCode\": \"PARAMETER_MISSING\",\n" +
                    "  \"errorMsg\": \"Parameter %1 shall be mandatory\"\n" +
                    "}";
        } else if (!"1".equals(req.getPin())) {
            strErr = "{\n" +
                    "  \"errorCode\": \"INCORRECT_PIN_CODE\",\n" +
                    "  \"errorMsg\": \"Pin code is incorrect for MSISDN %1\"\n" +
                    "}";
        }
        /*strErr = "{\n" +
                "  \"error\": \"MAX_ATTEMPTS_REACHED\",\n" +
                "  \"error_description\": \"Maximum login attempts reached\"\n" +
                "}";*/
        /*strErr = "{\n" +
                "  \"errorCode\": \"MSISDN_NOT_FOUND\",\n" +
                "  \"errorMsg\": \"MSISDN %1 is not found in the system\"\n" +
                "}";*/
        String strErr_500 = "{\n" +
                "  \"errorCode\": \"SERVER_INTERNAL_ERROR\",\n" +
                "  \"errorMsg\": \"<Specific Internal Error Description>\"\n" +
                "}";
        if (!"{}".equals(strErr)) {
            errRes = objectMapper.readValue(strErr, SESTriggerCommonRes.class);
        }

        return ResponseEntity.status(HttpStatus.OK).body(!"{}".equals(strErr) ? errRes : strErr);
//        return ResponseEntity.status(HttpStatus.OK).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.OK).body(errRes);//401
//        return ResponseEntity.status(HttpStatus.OK).body(errRes);//404
//        return ResponseEntity.status(HttpStatus.OK).body(errRes);//500
    }

    // URL:
    // http://localhost:8080/SomeContextPath/activateEsimSubscription
    @RequestMapping(value = "/esimservice/v1.0/activateEsimSubscription", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity activateEsimSubscription(@RequestBody SESActivateEsimSubscriptionReq req) throws IOException {
        logger.info("SESRESTEmulatorController activateEsimSubscription: " + req.toString());
        SESActivateEsimSubscriptionRes res = null;
        SESTriggerCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();

        //
        /*The activation status of the eSIM service subscription. The enumerated values are as follows:
            - New
            - Active
            - In-Progress
            - Inactive
            - Unusable*/
        String strResult = "{\n" +
                "  \"activationCode\": \"LPA:1$gpc.prod.ondemandconnectivity.com$UUY0VU4SP0URMEVF\",\n" +
                "  \"imsi\": \"452022220184349\",\n" +
                "  \"msisdn\": \"84812322201\",\n" +
                "  \"activationStatus\": \"Active\",\n" +
                "  \"iccidStatus\": \"Released\"\n" +
                "}";
        String strErr_400 = "{\n" +
                "  \"statusCode\": 400,\n" +
                "  \"errorCode\": \"INVALID_REQUEST\",\n" +
                "  \"errorMsg\": \"<Specific Error Description>\"\n" +
                "}";
        String strErr_403 = "{\n" +
                "  \"statusCode\": 403,\n" +
                "  \"errorCode\": \"ACTIVATION_ONGIONG_OR_COMPLETED\",\n" +
                "  \"errorMsg\": \"<Specific Error Description>\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"statusCode\": 500,\n" +
                "  \"errorCode\": \"CREATE_ESIM_SUBSCRIPTION_ERROR\",\n" +
                "  \"errorMsg\": \"Something didn't go as planned. Please try again through the menu on your phone.\"\n" +
                "}";
        res = objectMapper.readValue(strResult, SESActivateEsimSubscriptionRes.class);
//        errRes = objectMapper.readValue(strErr_400, SESTriggerCommonRes.class);
//        errRes = objectMapper.readValue(strErr_403, SESTriggerCommonRes.class);
        errRes = objectMapper.readValue(strErr_500, SESTriggerCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//403
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }

    // URL:
    // http://localhost:8080/SomeContextPath/esimSubscription
    @RequestMapping(value = "/ses/customercare/v2/esimSubscription", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity esimSubscription(@RequestParam Map<String, String> params) throws IOException {
        logger.info("SESRESTEmulatorController esimSubscription: " + params.toString());
        EsimSubscriptionsRes res = null;
        SESTriggerCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult = "{\n" +
                "  \"esimSubscriptions\": [\n" +
                "    {\n" +
                "      \"iccid\": \"356935435417269\",\n" +
                "      \"eid\": \"8986010110231170983S\",\n" +
                "      \"isPos\": 0,\n" +
                "      \"primaryImsi\": \"312019876543210\",\n" +
                "      \"primaryMsisdn\": \"14255833210\",\n" +
                "      \"imsi\": \"312019871234210\",\n" +
                "      \"msisdn\": \"14255833288\",\n" +
                "      \"iccidProfileType\": \"postpaid\",\n" +
                "      \"provisionStartTime\": 0,\n" +
                "      \"provisionDeactivateTime\": 0,\n" +
                "      \"activationStatus\": \"Active\",\n" +
                "      \"iccidStatus\": \"Released\",\n" +
                "      \"lockStatus\": 0,\n" +
                "      \"applicationCategory\": \"gearsubscription\",\n" +
                "      \"activationMode\": 0,\n" +
                "      \"serviceType\": 1,\n" +
                "      \"lastUpdateTime\": \"2021-06-13T09:18:00Z\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"iccid\": \"89840200022201843492\",\n" +
                "      \"eid\": \"89049032100005550100043500055345\",\n" +
                "      \"isPos\": 0,\n" +
                "      \"primaryImsi\": \"452021062010952\",\n" +
                "      \"primaryMsisdn\": \"84918022686\",\n" +
                "      \"imsi\": \"452022220184349\",\n" +
                "      \"msisdn\": \"84812322201\",\n" +
                "      \"iccidProfileType\": \"postpaid\",\n" +
                "      \"provisionStartTime\": 0,\n" +
                "      \"provisionDeactivateTime\": 0,\n" +
                "      \"activationStatus\": \"Active\",\n" +
                "      \"iccidStatus\": \"Installed\",\n" +
                "      \"lockStatus\": 0,\n" +
                "      \"applicationCategory\": \"gearsubscription\",\n" +
                "      \"activationMode\": 0,\n" +
                "      \"serviceType\": 1,\n" +
                "      \"lastUpdateTime\": \"2021-07-13T09:18:00Z\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        //
        String strErr_400 = "{\n" +
                "  \"statusCode\": 400,\n" +
                "  \"errorCode\": \"REQUEST_INVALID\",\n" +
                "  \"errorMsg\": \"The format or value of <specific field> is invalid.\"\n" +
                "}";
        String strErr_404 = "{\n" +
                "  \"statusCode\": 404,\n" +
                "  \"errorCode\": \"PRIMARY_MSISDN_NOT_EXIST\",\n" +
                "  \"errorMsg\": \"Primary MSISDN does not exist in system.\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"statusCode\": 500,\n" +
                "  \"errorCode\": \"BACKEND_SYSTEM_ERROR\",\n" +
                "  \"errorMsg\": \"<operator specific system error description>.\"\n" +
                "}";
        //
        res = objectMapper.readValue(strResult, EsimSubscriptionsRes.class);
        errRes = objectMapper.readValue(strErr_400, SESTriggerCommonRes.class);
//        errRes = objectMapper.readValue(strErr_404, SESTriggerCommonRes.class);
//        errRes = objectMapper.readValue(strErr_500, SESTriggerCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errRes);//404
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }
/*-----------------------------------------------------------------------------------------------------------------*/

    // URL:
    // http://localhost:8080/SomeContextPath/triggerSubscriptionActivation
    @RequestMapping(value = "/esim/triggerSubscriptionActivation", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity triggerSubscriptionActivation(@RequestBody SubscriptionActivationReq req) throws IOException {
        logger.info("SESRESTEmulatorController triggerSubscriptionActivation: " + req.toString());
        SubscriptionActivationRes res = null;
        SESTriggerCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult = "{\n" +
                "  \"unique-id\": \"8986010110231170983S\",\n" +
                "  \"serviceInfo\": {\n" +
                "    \"imsi\": \"31001987654321\",\n" +
                "    \"msisdn\": \"8613922114719\",\n" +
                "    \"subscriptionStatus\": \"Active\",\n" +
                "    \"provisionStatus\": 1,\n" +
                "    \"networkRegistrationInterval\": 10\n" +
                "  },\n" +
                "  \"iccidInfo\": {\n" +
                "    \"iccid\": \"89014104277527978255\",\n" +
                "    \"activationCode\": \"1$SMDP.GSMA.COM$04386-AGYFT-A74Y8-3F815\",\n" +
                "    \"confirmationCode\": \"797836\",\n" +
                "    \"smdpAddress\": \"http://smdp.carrier.com\",\n" +
                "    \"useSmds\": false,\n" +
                "    \"iccidStatus\": \"Released\",\n" +
                "    \"delayedProfileDownloadTime\": 30\n" +
                "  }\n" +
                "}";
        String strErr_400 = "{\n" +
                "  \"errorCode\": \"INVALID_REQUEST\",\n" +
                "  \"errorMsg\": \"<Specific Error Description>\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"errorCode\": \"SUBSCRIPTION_ACTIVATION_ERROR\",\n" +
                "  \"errorMsg\": \"Carreir backend system error, <specific error reason>.\"\n" +
                "}";
        res = objectMapper.readValue(strResult, SubscriptionActivationRes.class);
        errRes = objectMapper.readValue(strErr_500, SESTriggerCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }

    // URL:
    // http://localhost:8080/SomeContextPath/triggerSubscriptionRecovery
    @RequestMapping(value = "/esim/triggerSubscriptionRecovery", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity triggerSubscriptionRecovery(@RequestBody SubscriptionRecoveryReq req) throws IOException {
        logger.info("SESRESTEmulatorController triggerSubscriptionRecovery: " + req.toString());
        SubscriptionRecoveryRes res = null;
        SESTriggerCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult = "{\n" +
                "  \"unique-id\": \"8986010110231170983S\",\n" +
                "  \"serviceInfo\": {\n" +
                "    \"imsi\": \"31001987654321\",\n" +
                "    \"msisdn\": \"8613922114719\",\n" +
                "    \"subscriptionStatus\": \"Active\",\n" +
                "    \"provisionStatus\": 7,\n" +
                "    \"networkRegistrationInterval\": 10\n" +
                "  },\n" +
                "  \"iccidInfo\": {\n" +
                "    \"iccid\": \"89014104277527978255\",\n" +
                "    \"activationCode\": \"1$SMDP.GSMA.COM$04386-AGYFT-A74Y8-3F815\",\n" +
                "    \"confirmationCode\": \"797836\",\n" +
                "    \"smdpAddress\": \"http://smdp.carrier.com\",\n" +
                "    \"useSmds\": false,\n" +
                "    \"iccidStatus\": \"Released\",\n" +
                "    \"delayedProfileDownloadTime\": 30\n" +
                "  }\n" +
                "}";
        String strErr_400 = "{\n" +
                "  \"errorCode\": \"INVALID_REQUEST\",\n" +
                "  \"errorMsg\": \"<Specific Error Description>\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"errorCode\": \"SUBSCRIPTION_RECOVERY_ERROR\",\n" +
                "  \"errorMsg\": \"Carreir backend system error, <specific error reason>.\"\n" +
                "}";
        res = objectMapper.readValue(strResult, SubscriptionRecoveryRes.class);
        errRes = objectMapper.readValue(strErr_400, SESTriggerCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }

    // URL:
    // http://localhost:8080/SomeContextPath/triggerSubscriptionDeactivation
    @RequestMapping(value = "/esim/triggerSubscriptionDeactivation", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity triggerSubscriptionDeactivation(@RequestBody SubscriptionDeactivationReq req) throws IOException {
        logger.info("SESRESTEmulatorController triggerSubscriptionDeactivation: " + req.toString());
        SubscriptionDeactivationRes res = null;
        SESTriggerCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult = "{\n" +
                "  \"unique-id\": \"8986010110231170983S\",\n" +
                "  \"serviceInfo\": {\n" +
                "    \"imsi\": \"31001987654321\",\n" +
                "    \"msisdn\": \"8613922114719\",\n" +
                "    \"subscriptionStatus\": \"Inactive\",\n" +
                "    \"provisionStatus\": 4\n" +
                "  }\n" +
                "}";
        //
        String strErr_400 = "{\n" +
                "  \"errorCode\": \"INVALID_REQUEST\",\n" +
                "  \"errorMsg\": \"<Specific Error Description>\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"errorCode\": \"SUBSCRIPTION_DEACTIVATION_ERROR\",\n" +
                "  \"errorMsg\": \"Carreir backend system error, <specific error reason>.\"\n" +
                "}";
        //
        res = objectMapper.readValue(strResult, SubscriptionDeactivationRes.class);
        errRes = objectMapper.readValue(strErr_400, SESTriggerCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }

    // URL:
    // http://localhost:8080/SomeContextPath/carrierPlan
    @RequestMapping(value = "/carrierPlan", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity carrierPlan(@RequestParam Map<String, String> params) throws IOException {
        logger.info("SESRESTEmulatorController carrierPlan: " + params.toString());
        CarrierPlanRes res = null;
        SESCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult_IMSI_MSISDN = "{\n" +
                "  \"plans-list\": [\n" +
                "    {\n" +
                "      \"plan-category\": 1,\n" +
                "      \"plan-group-options\": [\n" +
                "        {\n" +
                "          \"plan-id\": \"domestic01\",\n" +
                "          \"plan-type\": \"postpaid\",\n" +
                "          \"plan-label\": \"2GB\",\n" +
                "          \"plan-description\": \"A domestic plan\",\n" +
                "          \"plan-value\": \"USD10\",\n" +
                "          \"plan-volume\": 5,\n" +
                "          \"plan-subscribed\": \"subscribed\",\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/2bg\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/2bg/t&c\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"plan-id\": \"domestic02\",\n" +
                "          \"plan-type\": \"prepaid\",\n" +
                "          \"plan-label\": \"National Shared Plan\",\n" +
                "          \"plan-description\": \"A shared plan\",\n" +
                "          \"plan-value\": \"USD20\",\n" +
                "          \"plan-volume\": 10,\n" +
                "          \"plan-subscribed\": \"not-subscribed\",\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/shared_plan\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/shared_plan/t&c\",\n" +
                "          \"plan-purchase-url\": \"https://fqdn/plans/otp\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"plan-id\": \"domestic03\",\n" +
                "          \"plan-label\": \"National Unlimited\",\n" +
                "          \"plan-value\": \"USD50\",\n" +
                "          \"plan-subscribed\": false,\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/unlimited\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/unlimited/t&c\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"plan-category\": 3,\n" +
                "      \"plan-group-options\": [\n" +
                "        {\n" +
                "          \"plan-id\": \"roaming01\",\n" +
                "          \"plan-label\": \"200MB (Free)\",\n" +
                "          \"plan-value\": \"USD10\",\n" +
                "          \"plan-subscribed\": \"subscribed\",\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/200gb\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/200mb/t&c\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"plan-id\": \"roaming02\",\n" +
                "          \"plan-label\": \"Passport Plus\",\n" +
                "          \"plan-value\": \"USD20\",\n" +
                "          \"plan-subscribed\": \"not-subscribed\",\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/pass_plus\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/pass_plus/t&c\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"plan-id\": \"roaming03\",\n" +
                "          \"plan-label\": \"Unlimited North America\",\n" +
                "          \"plan-value\": \"USD50\",\n" +
                "          \"plan-subscribed\": \"not-subscribed\",\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/unlimited\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/unlimited/t&c\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"more-plans-url\": \"https://www.carreir_plans/overview\"\n" +
                "}";
        String strResult_Plan_ID = "{\n" +
                "  \"plans-list\": [\n" +
                "    {\n" +
                "      \"plan-category\": 1,\n" +
                "      \"plan-group-options\": [\n" +
                "        {\n" +
                "          \"plan-id\": \"domestic01\",\n" +
                "          \"plan-label\": \"2GB\",\n" +
                "          \"plan-value\": \"USD10\",\n" +
                "          \"plan-volume\": 5,\n" +
                "          \"is-default-plan\": true,\n" +
                "          \"plan-description\": \"A domestic plan\",\n" +
                "          \"plan-billing-cycle\": 2,\n" +
                "          \"plan-subscribed\": \"subscribed\",\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/2bg\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/2bg/t&c\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String strResult_eSIM = "{\n" +
                "  \"plans-list\": [\n" +
                "    {\n" +
                "      \"plan-group-options\": [\n" +
                "        {\n" +
                "          \"plan-id\": \"domestic01\",\n" +
                "          \"plan-label\": \"2GB\",\n" +
                "          \"plan-value\": \"USD10\",\n" +
                "          \"plan-volume\": 5,\n" +
                "          \"is-default-plan\": true,\n" +
                "          \"plan-billing-cycle\": 2,\n" +
                "          \"plan-subscribed\": \"subscribed\",\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/2bg\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/2bg/t&c\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"plan-id\": \"domestic02\",\n" +
                "          \"plan-type\": \"prepaid\",\n" +
                "          \"plan-label\": \"National Shared Plan\",\n" +
                "          \"plan-value\": \"USD20\",\n" +
                "          \"plan-volume\": 10,\n" +
                "          \"plan-description\": \"A shared plan\",\n" +
                "          \"plan-billing-cycle\": 2,\n" +
                "          \"plan-subscribed\": \"not-subscribed\",\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/shared_plan\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/shared_plan/t&c\",\n" +
                "          \"plan-purchase-url\": \"https://fqdn/plans/otp\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"plan-id\": \"domestic03\",\n" +
                "          \"plan-label\": \"National Unlimited\",\n" +
                "          \"plan-value\": \"USD50\",\n" +
                "          \"plan-subscribed\": false,\n" +
                "          \"plan-purchasable\": true,\n" +
                "          \"plan-details-url\": \"https://fqdn/plans/unlimited\",\n" +
                "          \"plan-termcond-url\": \"https://fqdn/plans/unlimited/t&c\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"more-plans-url\": \"https://www.carreir_plans/overview\"\n" +
                "}";
        String strResult_5G = "{\n" +
                "  \"plans-list\": [\n" +
                "    {\n" +
                "      \"plan-group-options\": [\n" +
                "        {\n" +
                "          \"plan-volume\": 10,\n" +
                "          \"plan-supported-rat-list\": [\n" +
                "            \"mmw\"\n" +
                "          ],\n" +
                "          \"plan-overrun-policy\": 0\n" +
                "        },\n" +
                "        {\n" +
                "          \"plan-volume\": 50,\n" +
                "          \"plan-supported-rat-list\": [\n" +
                "            \"sub6\"\n" +
                "          ],\n" +
                "          \"plan-overrun-policy\": 1\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        //
        String strErr_400 = "{\n" +
                "  \"error\": \"REQUEST_INVALID\",\n" +
                "  \"error_description\": \"The format or value of <specific field> is invalid.\"\n" +
                "}";
        String strErr_404 = "{\n" +
                "  \"error\": \"PRIMARY_MSISDN_NOT_EXIST\",\n" +
                "  \"error_description\": \"Primary MSISDN does not exist in system.\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"error\": \"BACKEND_SYSTEM_ERROR\",\n" +
                "  \"error_description\": \"<operator specific system error description>.\"\n" +
                "}";
        //
        res = objectMapper.readValue(strResult_IMSI_MSISDN, CarrierPlanRes.class);
        errRes = objectMapper.readValue(strErr_400, SESCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errRes);//404
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }

    // URL:
    // http://localhost:8080/SomeContextPath/entitlements
    @RequestMapping(value = "/entitlements", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity getEntitlement(@RequestBody GetEntitlementReq req) throws IOException {
        logger.info("SESRESTEmulatorController getEntitlement: " + req.toString());
        GetEntitlementRes res = null;
        SESCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult = "{\n" +
                "  \"status\": 6000,\n" +
                "  \"response\": [\n" +
                "    {\n" +
                "      \"entitlement-name\": \"iCloudVoWiFi\",\n" +
                "      \"entitlement-status\": 6100\n" +
                "    },\n" +
                "    {\n" +
                "      \"entitlement-name\": \"MCA\",\n" +
                "      \"entitlement-status\": 6101,\n" +
                "      \"error-description\": \"Subscriber plan doesn’t contain this service\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"subscription-id\": [\n" +
                "    \"PlanA\",\n" +
                "    \"PlanB\"\n" +
                "  ],\n" +
                "  \"subscriber-specific-info\": {\n" +
                "    \"language\": \"EN\",\n" +
                "    \"other-info\": {\n" +
                "      \"xxxx-yyyy-zzzz-wwww\": \"AWSF\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        String strErr_400 = "{\n" +
                "  \"error\": \"INVALID_PARAMETERS\",\n" +
                "  \"error_description\": \"Invalid parameters\"\n" +
                "}";
        String strErr_404 = "{\n" +
                "  \"error\": \"IMSI_NOT_EXIST\",\n" +
                "  \"error_description\": \"IMSI doesn’t exist in system\"\n" +
                "}";
        res = objectMapper.readValue(strResult, GetEntitlementRes.class);
        errRes = objectMapper.readValue(strErr_404, SESCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//404
    }

    // URL:
    // http://localhost:8080/SomeContextPath/syncEsimSubInfo
    @RequestMapping(value = "/esim/syncEsimSubInfo", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity syncEsimSubInfo(@RequestBody SyncEsimSubInfoReq req) throws IOException {
        logger.info("SESRESTEmulatorController syncEsimSubInfo: " + req.toString());
        SyncEsimSubInfoRes res = null;
        SESCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult = "{\n" +
                "  \"eSimSubscriptionList\": [\n" +
                "    {\n" +
                "      \"subType\": 1,\n" +
                "      \"iccid\": \"356935435417269\",\n" +
                "      \"matchingId\": \"AAAAA-BBBBB-CCCCC-DDDDD\",\n" +
                "      \"eid\": \"8986010110231170983S\",\n" +
                "      \"isPos\": 0,\n" +
                "      \"imei\": \"356979067872692\",\n" +
                "      \"meid\": \"35697906787269\",\n" +
                "      \"deviceType\": \"Apple Watch\",\n" +
                "      \"displayName\": \"George’s watch share number\",\n" +
                "      \"selectedPlanId\": \"XXBUF101\",\n" +
                "      \"primaryImsi\": \"312019876543210\",\n" +
                "      \"primaryMsisdn\": \"14255833210\",\n" +
                "      \"imsi\": \"312019871234210\",\n" +
                "      \"msisdn\": \"14255833288\",\n" +
                "      \"iccidProfileType\": \"postpaid\",\n" +
                "      \"iccidStatus\": \"Released\",\n" +
                "      \"activationStatus\": \"Active\",\n" +
                "      \"lockStatus\": 0,\n" +
                "      \"alternateSmdpFqdn\": \"http://esim.test.com\",\n" +
                "      \"gsmaSgp22Version\": \"2.2\",\n" +
                "      \"simAllianceProfileVersion\": \"2.0.0\",\n" +
                "      \"billingAccountNumber\": \"Bill Account 1\",\n" +
                "      \"activateBy\": \"356935435417269\",\n" +
                "      \"useDs\": false,\n" +
                "      \"lastModifiedTime\": 1585076890\n" +
                "    },\n" +
                "    {\n" +
                "      \"subType\": 2,\n" +
                "      \"iccid\": \"356935435417270\",\n" +
                "      \"eid\": \"8986010110231170984S\",\n" +
                "      \"isPos\": 0,\n" +
                "      \"imei\": \"356979067872693\",\n" +
                "      \"meid\": \"35697906787270\",\n" +
                "      \"deviceType\": \"Apple Watch\",\n" +
                "      \"displayName\": \"George’s watch standalone number\",\n" +
                "      \"selectedPlanId\": \"XXBUF102\",\n" +
                "      \"imsi\": \"312019871234211\",\n" +
                "      \"msisdn\": \"14255833299\",\n" +
                "      \"iccidProfileType\": \"postpaid\",\n" +
                "      \"iccidStatus\": \"Released\",\n" +
                "      \"activationStatus\": \"Active\",\n" +
                "      \"lockStatus\": 0,\n" +
                "      \"alternateSmdpFqdn\": \"http://esim.test.com\",\n" +
                "      \"gsmaSgp22Version\": \"2.2\",\n" +
                "      \"simAllianceProfileVersion\": \"2.0.0\",\n" +
                "      \"billingAccountNumber\": \"Bill Account 1\",\n" +
                "      \"activateBy\": \"356935435417269\",\n" +
                "      \"useDs\": false\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String strErr_400 = "{\n" +
                "  \"error\": \"Invalid Request\",\n" +
                "  \"error_description\": \"The request is invalid, <Specific invalid parameter>.\"\n" +
                "}";
        String strErr_404 = "{\n" +
                "  \"error\": \"Subscription Not Found\",\n" +
                "  \"error_description\": \"No esim subscription found in the backend system\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"error\": \"Backend System Error\",\n" +
                "  \"error_description\": \"“<Specific error description>.\"\n" +
                "}";
        res = objectMapper.readValue(strResult, SyncEsimSubInfoRes.class);
        errRes = objectMapper.readValue(strErr_404, SESCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//404
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }

    // URL:
    // http://localhost:8080/SomeContextPath/useridentities/msisdn
    @RequestMapping(value = "/useridentities/msisdn", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity IMSI2MSISDN(@RequestParam String imsi) throws IOException {
        logger.info("SESRESTEmulatorController IMSI2MSISDN: imsi = " + imsi);
        IMSI2MSISDNRes res = null;
        SESCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult = "{\n" +
                "  \"msisdn\": \"151429828282\",\n" +
                "  \"isOverWritten\": true,\n" +
                "  \"originalMsisdn\": \"151429828284\",\n" +
                "  \"msisdnStatus\": 1\n" +
                "}";
        String strErr_404 = "{\n" +
                "  \"error\": \"MSISDN_NOT_EXIST\",\n" +
                "  \"error_description\": \"MSISDN does not exist in system\"\n" +
                "}";
        String strErr_400 = "{\n" +
                "  \"error\": \"IMSI_MISSING\",\n" +
                "  \"error_description\": \"IMSI shall be input\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"error\": \"BACKEND_SYSTEM_ERROR\",\n" +
                "  \"error_description\": \"<operator specific system error description>.\"\n" +
                "}";
        res = objectMapper.readValue(strResult, IMSI2MSISDNRes.class);
        errRes = objectMapper.readValue(strErr_404, SESCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//404
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }

    // URL:
    // http://localhost:8080/SomeContextPath/eligibilityCheck
    @RequestMapping(value = "/esim/eligibilityCheck", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity eligibilityCheck(@RequestBody EligibilityCheckReq req) throws IOException {
        logger.info("SESRESTEmulatorController eligibilityCheck: " + req.toString());
        EligibilityCheckRes res = null;
        SESCommonRes errRes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        //
        String strResult = "{\n" +
                "  \"result-code\": 6000\n" +
                "}";
        String strErr_400 = "{\n" +
                "  \"error\": \"Invalid_Request\",\n" +
                "  \"error_description\": \"The request is invalid, <Specific invalid parameter>.\"\n" +
                "}";
        String strErr_404 = "{\n" +
                "  \"error\": \"Primary_Device_Not_Found\",\n" +
                "  \"error_description\": \"The primary device not find in the backend system\"\n" +
                "}";
        String strErr_500 = "{\n" +
                "  \"error\": \"Internal Server Error\",\n" +
                "  \"error_description\": \"Error when check the eSIM eligibility, <Specific error description>.\"\n" +
                "}";
        res = objectMapper.readValue(strResult, EligibilityCheckRes.class);
        errRes = objectMapper.readValue(strErr_404, SESCommonRes.class);

        return ResponseEntity.status(HttpStatus.OK).body(res);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errRes);//400
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//404
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);//500
    }

    @RequestMapping(value = "/test", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Logger triggerSubscriptionDeactivation() throws IOException {
//        reporter.info("89840200022218000052 | LPA:1$rsp.truphone.com$JT-1O7ON7-VIJAP | 452022221800005 | Active | Released");
        return reporter;
    }
}
