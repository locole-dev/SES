package vn.com.telsoft.websheet.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.com.telsoft.websheet.api.constants.Const;
import vn.com.telsoft.websheet.api.model.ActivateEsimDeviceReq;
import vn.com.telsoft.websheet.api.model.ManageEsimDeviceReq;
import vn.com.telsoft.websheet.api.model.RecoveryEsimDeviceReq;
import vn.com.telsoft.websheet.api.util.CipherUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/websheet/esim/generic/companion")
public class MainRESTController {
    public static final Logger logger = LoggerFactory.getLogger(MainRESTController.class);

    static final String URL_activateEsimDevice = "/activateEsimDevice";
    static final String URL_manageEsimDevice = "/manageEsimDevice";
    static final String URL_recoveryEsimDevice = "/recoveryEsimDevice";

    @RequestMapping(value = "/signup", //
            method = {RequestMethod.GET, RequestMethod.POST}, //
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_HTML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_HTML_VALUE})
    public ModelAndView activateEsimDevice(@RequestBody ActivateEsimDeviceReq req, HttpServletRequest request) throws IOException {
        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        logger.info("request: " + req.toString());
//        String requestURI = request.getRequestURI();
        String token = "";
        if (req.getWebsheetSessionToken() != null && !"".equals(req.getWebsheetSessionToken())) {
            token = CipherUtils.decrypt(req.getWebsheetSessionToken());
        } else {
            token = CipherUtils.decrypt(req.getCarrierPostData());
        }
        Map<String, String> mpToken = CipherUtils.getQueryParamsMap(token);
        //put session
        request.getSession().setAttribute(Const.SESSION_KEY.IS_AUTHORIZED, "false");
        request.getSession().setAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN, mpToken);
        String isFactory = mpToken.get(Const.WEBSHEET_SESSION_TOKEN.IS_FACTORY);
        if ("false".equals(isFactory)) {
            request.getSession().setAttribute(Const.SESSION_KEY.PREV_URI, URL_activateEsimDevice);
        } else {
            request.getSession().setAttribute(Const.SESSION_KEY.PREV_URI, URL_recoveryEsimDevice);
        }
        //
        return new ModelAndView("redirect:/#/signin");
    }

    @RequestMapping(value = "/signupTest", //
            method = {RequestMethod.GET, RequestMethod.POST}, //
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView activateEsimDeviceTest(@ModelAttribute(name = "debug") String req, HttpServletRequest request) throws IOException {
        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        ObjectMapper objMapper = new ObjectMapper();
        ActivateEsimDeviceReq activateEsimDeviceReq = objMapper.readValue(req, ActivateEsimDeviceReq.class);
        //
        String requestURI = request.getRequestURI();
        String token = "";
        if (activateEsimDeviceReq.getWebsheetSessionToken() != null && !"".equals(activateEsimDeviceReq.getWebsheetSessionToken())) {
            token = CipherUtils.decrypt(activateEsimDeviceReq.getWebsheetSessionToken());
        } else {
            token = CipherUtils.decrypt(activateEsimDeviceReq.getCarrierPostData());
        }
        Map<String, String> mpToken = CipherUtils.getQueryParamsMap(token);
        //put session
        request.getSession().setAttribute(Const.SESSION_KEY.IS_AUTHORIZED, "false");
        request.getSession().setAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN, mpToken);
        String isFactory = mpToken.get(Const.WEBSHEET_SESSION_TOKEN.IS_FACTORY);
        if ("false".equals(isFactory)) {
            request.getSession().setAttribute(Const.SESSION_KEY.PREV_URI, URL_activateEsimDevice);
        } else {
            request.getSession().setAttribute(Const.SESSION_KEY.PREV_URI, URL_recoveryEsimDevice);
        }
        //
        return new ModelAndView("redirect:/#/signin");
    }

    @RequestMapping(value = "/manageaccount", //
            method = {RequestMethod.GET, RequestMethod.POST}, //
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_HTML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_HTML_VALUE})
    public ModelAndView manageEsimDevice(@RequestBody ManageEsimDeviceReq req, HttpServletRequest request) {
        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
//        String requestURI = request.getRequestURI();
        String token = "";
        if (req.getWebsheetSessionToken() != null && !"".equals(req.getWebsheetSessionToken())) {
            token = CipherUtils.decrypt(req.getWebsheetSessionToken());
        } else {
            token = CipherUtils.decrypt(req.getCarrierPostData());
        }
        Map<String, String> mpToken = CipherUtils.getQueryParamsMap(token);
        //put session
        request.getSession().setAttribute(Const.SESSION_KEY.IS_AUTHORIZED, "false");
        request.getSession().setAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN, mpToken);
        request.getSession().setAttribute(Const.SESSION_KEY.PREV_URI, URL_manageEsimDevice);
        //
        return new ModelAndView("redirect:/#/signin");
    }

    @RequestMapping(value = "/manageaccountTest", //
            method = {RequestMethod.POST}, //
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView manageEsimDeviceTest(@ModelAttribute(name = "debug") String req, HttpServletRequest request) throws IOException {
        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        ObjectMapper objMapper = new ObjectMapper();
        ManageEsimDeviceReq manageEsimDeviceReq = objMapper.readValue(req, ManageEsimDeviceReq.class);
        //
//        String requestURI = request.getRequestURI();
        String token = "";
        if (manageEsimDeviceReq.getWebsheetSessionToken() != null && !"".equals(manageEsimDeviceReq.getWebsheetSessionToken())) {
            token = CipherUtils.decrypt(manageEsimDeviceReq.getWebsheetSessionToken());
        } else {
            token = CipherUtils.decrypt(manageEsimDeviceReq.getCarrierPostData());
        }
        Map<String, String> mpToken = CipherUtils.getQueryParamsMap(token);
        //put session
        request.getSession().setAttribute(Const.SESSION_KEY.IS_AUTHORIZED, "false");
        request.getSession().setAttribute(Const.SESSION_KEY.PREV_URI, URL_manageEsimDevice);
        request.getSession().setAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN, mpToken);
        //
        return new ModelAndView("redirect:/#/signin");
    }

    @RequestMapping(value = "/themeTest", method = {RequestMethod.GET})
    public ModelAndView themeTest(HttpServletRequest request) throws IOException {
        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        return new ModelAndView("redirect:/#/signin");
    }

    /*@RequestMapping(value = "/recoveryEsimDevice", //
            method = {RequestMethod.GET, RequestMethod.POST}, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    public ModelAndView recoveryEsimDevice(@RequestBody RecoveryEsimDeviceReq req, HttpServletRequest request) {
        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        String requestURI = request.getRequestURI();
        String token = "";
        if (req.getWebsheetSessionToken() != null && !"".equals(req.getWebsheetSessionToken())) {
            token = CipherUtils.decrypt(req.getWebsheetSessionToken());
        } else {
            token = CipherUtils.decrypt(req.getCarrierPostData());
        }
        Map<String, String> mpToken = CipherUtils.getQueryParamsMap(token);
        //put session
        request.getSession().setAttribute(Const.SESSION_KEY.IS_AUTHORIZED, "false");
        request.getSession().setAttribute(Const.SESSION_KEY.PREV_URI, URL_recoveryEsimDevice);
        request.getSession().setAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN, mpToken);
        //
        return new ModelAndView("redirect:/#/signin");
    }*/

    /*@RequestMapping(value = "/recoveryEsimDeviceTest", //
            method = {RequestMethod.POST}, //
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView recoveryEsimDeviceTest(@ModelAttribute(name = "debug") String req, HttpServletRequest request) throws IOException {
        logger.info("-------------------------------------------------------------------------------------------------------------");
        logger.info("SessionId: " + request.getSession().getId());
        ObjectMapper objMapper = new ObjectMapper();
        RecoveryEsimDeviceReq recoveryEsimDeviceReq = objMapper.readValue(req, RecoveryEsimDeviceReq.class);
        //
        String requestURI = request.getRequestURI();
        String token = "";
        if (recoveryEsimDeviceReq.getWebsheetSessionToken() != null && !"".equals(recoveryEsimDeviceReq.getWebsheetSessionToken())) {
            token = CipherUtils.decrypt(recoveryEsimDeviceReq.getWebsheetSessionToken());
        } else {
            token = CipherUtils.decrypt(recoveryEsimDeviceReq.getCarrierPostData());
        }
        Map<String, String> mpToken = CipherUtils.getQueryParamsMap(token);
        //put session
        request.getSession().setAttribute(Const.SESSION_KEY.IS_AUTHORIZED, "false");
        request.getSession().setAttribute(Const.SESSION_KEY.PREV_URI, URL_recoveryEsimDevice);
        request.getSession().setAttribute(Const.SESSION_KEY.WEBSHEET_SESSION_TOKEN, mpToken);
        //
        return new ModelAndView("redirect:/#/signin");
    }*/

}
