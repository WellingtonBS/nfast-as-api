package br.com.nfast.api.web;

import br.com.nfast.api.config.Config;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String index() {
        //return "redirect:swagger-ui/index.html";
        return "<p>NFast API " + Config.API_VERSION + "</p><p>Instance ID " + Config.INSTANCE_ID + "</p>";
    }

}
