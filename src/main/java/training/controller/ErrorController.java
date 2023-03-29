package training.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String PATH = "/error";
    @RequestMapping(value = PATH)
    @ResponseBody
    String error(HttpServletRequest request) {
        return "<h1>Error occurred</h1>";
    }

}
