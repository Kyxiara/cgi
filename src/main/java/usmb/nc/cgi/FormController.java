package usmb.nc.cgi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {

    @GetMapping("/form")
    public ModelAndView creationForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("inscriptionForm");
        return mv;
    }

}
