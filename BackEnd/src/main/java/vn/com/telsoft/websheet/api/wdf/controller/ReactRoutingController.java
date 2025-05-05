package vn.com.telsoft.websheet.api.wdf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 14/4/25
 */

@Controller
public class ReactRoutingController {

    @RequestMapping(value = {"/gui/{[path:[^\\.]*}", "/gui/**/{[path:[^\\.]*}"})
    public String forwardGui() {
        return "forward:/gui/index.html";
    }
}
