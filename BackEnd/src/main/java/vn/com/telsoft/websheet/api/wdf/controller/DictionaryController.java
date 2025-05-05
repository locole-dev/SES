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
import vn.com.telsoft.websheet.api.wdf.dto.DictionaryDataDto;
import vn.com.telsoft.websheet.api.wdf.model.Dictionary;
import vn.com.telsoft.websheet.api.wdf.service.DictionaryService;
import vn.com.telsoft.websheet.api.wdf.util.Const;
import vn.com.telsoft.websheet.api.wdf.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${wdf.request-path-prefix}")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;
    public static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @RequestMapping(value = "/listDictionary",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity listDictionary() throws IOException, ServletException {
        logger.info("DictionaryController listDictionary: ");
        List<Dictionary> list = dictionaryService.findAllDictionaries();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveDictionary",
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveDictionary(@RequestBody JsonNode payload, HttpServletRequest request) throws IOException {
        logger.info("DictionaryController saveDictionary: " + payload);
        dictionaryService.saveDictionary(new JSONObject(payload.toString()), request.getMethod());
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getDictionary/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getDictionary(@PathVariable("id") Long id) throws IOException {
        logger.info("DictionaryController getDictionary: " + id);
        Dictionary dictionary = dictionaryService.findDictionaryById(id);
        return new ResponseEntity<>(dictionary, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteDictionary/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteDictionary(@PathVariable("id") Long id) throws IOException {
        logger.info("DictionaryController deleteDictionary: " + id);
        dictionaryService.deleteDictionaryById(id);
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getDictionaryBySmartWatchKey/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getDictionaryBySmartWatchKey(@PathVariable("id") Long id) throws IOException {
        logger.info("DictionaryController getDictionaryBySmartWatchKey: " + id);
        DictionaryDataDto data = dictionaryService.findDictionaryDataBySmartWatchKey(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
