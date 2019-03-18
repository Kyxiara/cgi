package usmb.nc.cgi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Controller
public class FormController {

    private String pdf_template_path = "src/main/resources/pdf_templates/";
    private String pdf_filled_path = "src/main/resources/pdf_filled/";

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
        FormFillingPdf formFillingPdf = new FormFillingPdf(pdf_template_path + "formulaire_inscription_hockey.pdf");
        try {
            formFillingPdf.fill(form.getHashMap(), pdf_filled_path + form.getFileName(), false);
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
            System.out.println("downloading : " + pdf_filled_path + fileName);
            InputStream is = new FileInputStream(pdf_filled_path + fileName);
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
