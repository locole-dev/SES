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
import vn.com.telsoft.websheet.api.wdf.dto.CarrierMappingsDTO;
import vn.com.telsoft.websheet.api.wdf.model.CarrierMappings;
import vn.com.telsoft.websheet.api.wdf.service.CarrierMappingsService;
import vn.com.telsoft.websheet.api.wdf.util.Const;
import vn.com.telsoft.websheet.api.wdf.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${wdf.request-path-prefix}")
public class CarrierMappingsController {
    @Autowired
    private CarrierMappingsService CarrierMappingsService;
    public static final Logger logger = LoggerFactory.getLogger(CarrierMappingsController.class);

    @RequestMapping(value = "/listCarrierMappingsByCarrier/{carrierId}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity listCarrierMappings(@PathVariable("carrierId") Long carrierId) throws IOException, ServletException {
        logger.info("CarrierMappingsController listCarrierMappings: ");
        List<CarrierMappingsDTO> list = CarrierMappingsService.findAllCarrierMappingsByCarrierId(carrierId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveCarrierMappings",
            method = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveCarrierMappings(@RequestBody JsonNode payload, HttpServletRequest request) throws IOException {
        logger.info("CarrierMappingsController listCarrierMappings: " + payload);
        CarrierMappingsService.saveCarrierMappings(new JSONObject(payload.toString()), request.getMethod());
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCarrierMappings/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveCarrierMappings(@PathVariable("id") Long id) throws IOException {
        logger.info("CarrierMappingsController getCarrierMappings: " + id);
        CarrierMappingsDTO carrierMappings = CarrierMappingsService.findCarrierMappingsById(id);
        return new ResponseEntity<>(carrierMappings, HttpStatus.OK);
    }
}
