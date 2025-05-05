package vn.com.telsoft.websheet.api.wdf.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDTO;
import vn.com.telsoft.websheet.api.wdf.dto.SmartWatchDtoV2;
import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;
import vn.com.telsoft.websheet.api.wdf.service.SmartWatchService;
import vn.com.telsoft.websheet.api.wdf.util.Const;
import vn.com.telsoft.websheet.api.wdf.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${wdf.request-path-prefix}")
public class SmartWatchController {
    @Autowired
    private SmartWatchService smartWatchService;
    public static final Logger logger = LoggerFactory.getLogger(SmartWatchController.class);

    @RequestMapping(value = "/listSmartWatch",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity listSmartWatch() throws IOException, ServletException {
        logger.info("SmartWatchController listSmartWatch: ");
        List<SmartWatchDTO> list = smartWatchService.findAllSmartWatches();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveSmartWatch",
            method = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveSmartWatch(@RequestBody JsonNode payload, HttpServletRequest request) throws IOException {
        logger.info("SmartWatchController saveSmartWatch: " + payload);
        smartWatchService.saveSmartWatch(new JSONObject(payload.toString()), request.getMethod());
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSmartWatch/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getSmartWatch(@PathVariable("id") Long id) throws IOException {
        logger.info("SmartWatchController getSmartWatch: " + id);
        SmartWatchDTO smartWatch = smartWatchService.findSmartWatchById(id);
        return new ResponseEntity<>(smartWatch, HttpStatus.OK);
    }

    @RequestMapping(value = "/listSmartWatchForDictionaryForm",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity listSmartWatchForDictionaryForm() throws IOException, ServletException {
        logger.info("SmartWatchController listSmartWatchForDictionaryForm: ");
        List<SmartWatchDtoV2> list = smartWatchService.findAllSmartWatchesForDictionaryForm();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
