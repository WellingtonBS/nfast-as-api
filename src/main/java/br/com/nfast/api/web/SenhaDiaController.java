package br.com.nfast.api.web;

import br.com.nfast.api.utils.SenhaDia;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SenhaDiaController {

    @RequestMapping(value = "/senha-dia")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String index() {
        return SenhaDia.get();
    }

}
