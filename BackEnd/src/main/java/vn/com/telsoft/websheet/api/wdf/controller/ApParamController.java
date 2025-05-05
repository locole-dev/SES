package vn.com.telsoft.websheet.api.wdf.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.telsoft.websheet.api.wdf.model.ApParam;
import vn.com.telsoft.websheet.api.wdf.service.ApParamService;
import vn.com.telsoft.websheet.api.wdf.util.Const;
import vn.com.telsoft.websheet.api.wdf.util.Utils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 12/3/25
 */

@RestController
@RequestMapping("${wdf.request-path-prefix}")
public class ApParamController {
    @Autowired
    private ApParamService apParamService;

    public static final Logger logger = LoggerFactory.getLogger(ApParamController.class);

    @RequestMapping(value = "/findAllApParam",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity findAllApParam() throws IOException, ServletException {
        logger.info("ApParamController findAllApParam");
        List<ApParam> list = apParamService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAllApParamByType/{type}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity findAllApParamByType(@PathVariable("type") String type) throws IOException, ServletException {
        logger.info("ApParamController findAllApParamByType: " + type);
        List<ApParam> list = apParamService.findAllByType(type);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveApParam",
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveApParam(@RequestBody JsonNode payload) throws IOException {
        logger.info("ApParamController saveApParam: " + payload);
        apParamService.saveApParam(Utils.getGson().fromJson(payload.toString(), ApParam.class));
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getApParam/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveCarrier(@PathVariable("id") Long id) throws IOException {
        logger.info("ApParamController getApParam: " + id);
        ApParam apParam = apParamService.getApParamById(id);
        return new ResponseEntity<>(apParam, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteApParam/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteApParam(@PathVariable("id") Long id) throws IOException {
        logger.info("ApParamController deleteApParam: " + id);
        apParamService.deleteApParamById(id);
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

}
