package app.presentation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/welcome")
    @ResponseBody
    ModelAndView welcome() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", "Welcome in sample Spring Boot application");
        ModelAndView mv = new ModelAndView("welcome", model);
        return mv;
    }
}
