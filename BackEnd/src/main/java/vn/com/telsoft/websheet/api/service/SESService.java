package vn.com.telsoft.websheet.api.service;

import vn.com.telsoft.websheet.api.model.*;

import java.util.Map;

public interface SESService {

    CommonRes validateUserByPin(ValidateUserByPinReq req) throws Exception;

    CommonRes activateEsimSubscription(SESActivateEsimSubscriptionReq req) throws Exception;

    CommonRes recoveryEsimSubscription(SESActivateEsimSubscriptionReq req) throws Exception;

    CommonRes esimSubscription(Map<String, String> params) throws Exception;

    CommonRes triggerSubscriptionActivation(SubscriptionActivationReq req) throws Exception;

    CommonRes triggerSubscriptionRecovery(SubscriptionRecoveryReq req) throws Exception;

    CommonRes triggerSubscriptionDeactivation(SubscriptionDeactivationReq req) throws Exception;

    CommonRes carrierPlan(Map<String, String> params) throws Exception;

    CommonRes getEntitlement(GetEntitlementReq req) throws Exception;

    CommonRes syncEsimSubInfo(SyncEsimSubInfoReq req) throws Exception;

    CommonRes IMSI2MSISDN(String imsi) throws Exception;

    CommonRes eligibilityCheck(EligibilityCheckReq req) throws Exception;
}
