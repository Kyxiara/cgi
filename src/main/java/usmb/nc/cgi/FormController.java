package usmb.nc.cgi;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FormController {

    private String pdf_template_path = "src/main/resources/pdf_templates/";

    @GetMapping("/form")
    public String creationForm(Model model) {
        model.addAttribute("form", new Form());
        return "inscriptionForm";
    }

    @PostMapping("/form")
    public String formPost(@ModelAttribute Form form) {
        FormFillingPdf formFillingPdf = new FormFillingPdf(pdf_template_path + "formulaire_inscription_hockey.pdf");
        try {
            formFillingPdf.fill(form.getHashMap(), pdf_template_path + form.getPdfName(), false);
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
}
