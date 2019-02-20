package usmb.nc.cgi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class FormController {

    @GetMapping("/form")
    public String creationForm(Model model) {
        model.addAttribute("form", new Form());
        return "inscriptionForm";
    }

    @PostMapping("/form")
    public String formPost(@ModelAttribute Form form) {
        FormFillingPdf formFillingPdf = new FormFillingPdf("src/main/resources/pdf_templates/formulaire_inscription_hockey.pdf");
        try {
            formFillingPdf.fill(form.getHashMap(), "src/main/resources/pdf_templates/inscription_realise.pdf", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "resultForm";
    }
}
