package usmb.nc.cgi;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class FormFillingPdf {

    private String template;


    public FormFillingPdf(String template){
        this.template = template;
    }

    public void fill(HashMap<String, String> values, String dest, boolean isDefinitive) throws IOException {
        PdfReader reader = new PdfReader(new FileInputStream(template));
        PdfStamper stamper = null;
        try {
            stamper = new PdfStamper(reader, new FileOutputStream(dest));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new IOException("can't create a pdfStamper from the template and given output path");
        }
        AcroFields fields = stamper.getAcroFields();
        HashMap<String, AcroFields.Item> myFields = (HashMap<String, AcroFields.Item>) fields.getFields();
        for (String field : values.keySet()) {
            if(myFields.get(field) != null){
                try {
                    fields.setField(field, values.get(field));
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                System.out.println("added : " + values.get(field) + " for " + field);
            } else {
                System.err.println("invalid field !!");
            }
        }
        if(isDefinitive)
            System.err.println("isDefinitive is not a supported feature at this time");
        stamper.setFormFlattening(isDefinitive);
        try {
            stamper.close();
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new IOException("can't close template file");
        }
        reader.close();
    }
}