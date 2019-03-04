package usmb.nc.cgi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Controller
public class FormController {

    @Autowired
    private FormRepository formRepository;

    @GetMapping("/form")
    public String creationForm(Model model) {
        model.addAttribute("form", new Form());
        return "inscriptionForm";
    }

    @PostMapping("/form")
    public String formPost(@ModelAttribute Form form) {
        formRepository.save(form);
        FormFillingPdf formFillingPdf = new FormFillingPdf("src/main/resources/pdf_templates/formulaire_inscription_hockey.pdf");
        try {
            formFillingPdf.fill(form.getHashMap(), "src/main/resources/pdf_templates/inscription_realise.pdf", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "resultForm";
    }

    @RequestMapping(value = "/pdf/{file_name}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("file_name") String fileName,
            HttpServletResponse response) {
        try {
            InputStream is = new FileInputStream("src/main/resources/pdf_templates/" + fileName);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            System.err.println("Error writing file to output stream. Filename was " + fileName);
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    @GetMapping("/listforms")
    public String listForms(Model model) {
        model.addAttribute("forms", (ArrayList<Form>) formRepository.findAll());
        return "listForms";
    }
}
