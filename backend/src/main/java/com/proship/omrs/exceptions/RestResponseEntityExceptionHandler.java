package com.proship.omrs.exceptions;

import com.proship.omrs.exceptions.customExceptions.OmrsResourceNotFoundException;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import java.sql.SQLException;
import java.util.Date;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail(request.getDescription(true));
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setTimestamp(new Date());
        return handleExceptionInternal(ex, errorDetail,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = {OmrsResourceNotFoundException.class, ResourceNotFoundException.class})
    protected ResponseEntity<Object>handleResourceNotFound(RuntimeException ex, WebRequest req) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date());
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDetail(req.getDescription(true));
        return handleExceptionInternal(ex,errorDetail,new HttpHeaders(),HttpStatus.NOT_FOUND,req);
    }
    @ExceptionHandler(value ={SQLException.class} )
    protected ResponseEntity<Object> handleSqlException(RuntimeException ex, WebRequest req){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setMessage(ex.getMessage());
        errorDetail.setDetail("Database error.");
        errorDetail.setTimestamp(new Date());
        return handleExceptionInternal(ex,errorDetail,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR,req);
    }
}