package usmb.nc.cgi;

public class FormNotFoundException extends RuntimeException {

    public FormNotFoundException(){

    }

    public FormNotFoundException(Long formId) {
        super("form: " + formId + " not found.");
    }
}
