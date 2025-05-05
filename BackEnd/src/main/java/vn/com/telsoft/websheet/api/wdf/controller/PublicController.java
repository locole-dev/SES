package vn.com.telsoft.websheet.api.wdf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.telsoft.websheet.api.wdf.model.AuthenticationRequest;
import vn.com.telsoft.websheet.api.wdf.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 12/3/25
 */

@RestController
@RequestMapping("${wdf.request-path-prefix}/public")
public class PublicController {
    @Autowired
    private AuthenticationService authenticationService;

    public static final Logger logger = LoggerFactory.getLogger(PublicController.class);

    @RequestMapping(value = "/login",
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity saveApParam(@RequestBody AuthenticationRequest payload, HttpServletRequest httpServletRequest) throws IOException {
        logger.info("PublicController login: " + payload);
        return authenticationService.authenticate(payload, httpServletRequest);
    }

}
