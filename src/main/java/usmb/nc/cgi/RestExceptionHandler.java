package usmb.nc.cgi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.ResponseEntity.notFound;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = {FormNotFoundException.class})
    public ResponseEntity vehicleNotFound(FormNotFoundException ex, WebRequest request) {
        System.out.println("handling VehicleNotFoundException...");
        return notFound().build();
    }
}