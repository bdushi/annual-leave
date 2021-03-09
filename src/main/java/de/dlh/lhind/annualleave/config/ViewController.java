package de.dlh.lhind.annualleave.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static de.dlh.lhind.annualleave.common.Common.PATH;

@Controller
public class ViewController implements ErrorController {

    @RequestMapping(value = {PATH})
    String error()  {
        return "forward:/index.html";
    }

    public String getErrorPath() {
        return PATH;
    }
}
