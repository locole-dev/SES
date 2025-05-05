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
import vn.com.telsoft.websheet.api.wdf.dto.CarrierDTO;
import vn.com.telsoft.websheet.api.wdf.service.CarrierService;
import vn.com.telsoft.websheet.api.wdf.util.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${wdf.request-path-prefix}")
public class CarrierController {
    @Autowired
    private CarrierService carrierService;
    public static final Logger logger = LoggerFactory.getLogger(CarrierController.class);

    @RequestMapping(value = "/listCarrier",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity listCarrier() throws IOException, ServletException {
        logger.info("CarrierController listCarrier: ");
        List<CarrierDTO> list = carrierService.findAllCarriers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveCarrier",
            method = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveCarrier(@RequestBody JsonNode payload, HttpServletRequest request) throws IOException {
        logger.info("CarrierController listCarrier: " + payload);
        carrierService.saveCarrier(new JSONObject(payload.toString()), request.getMethod());
        return new ResponseEntity<>(Const.ResponseFieldName.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCarrier/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveCarrier(@PathVariable("id") Long id) throws IOException {
        logger.info("CarrierController getCarrier: " + id);
        CarrierDTO carrier = carrierService.findCarrierById(id);
        return new ResponseEntity<>(carrier, HttpStatus.OK);
    }
}
