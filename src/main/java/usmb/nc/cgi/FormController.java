package usmb.nc.cgi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class FormController {

    private String pdf_template_path = "src/main/resources/pdf_templates/";
    private String pdf_filled_path = "src/main/resources/pdf_filled/";
    private String default_form = "formulaire_inscription_hockey.pdf";


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
        return "resultForm";
    }

   @RequestMapping(value="/pdf/{file_name}", method=RequestMethod.GET)
    public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
        try {
            Optional<Form> optionalForm = formRepository.findById(Form.findIdByFilename(fileName));
            if(optionalForm.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "form not found");

            Form form = optionalForm.get();
            System.out.println("downloading : " + pdf_filled_path + fileName);
            FormFillingPdf formFillingPdf = new FormFillingPdf(pdf_template_path + default_form);
            formFillingPdf.fill(form.getHashMap(), response.getOutputStream(), false);

            // Set the content type and attachment header.
            response.addHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("application/pdf");

            // copy it to response's OutputStream
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
