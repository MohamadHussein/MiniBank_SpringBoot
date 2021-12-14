package com.finance.minibank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        ExceptionPayload payload = new ExceptionPayload(entityNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException noDataFoundException){
        ExceptionPayload payload = new ExceptionPayload(
                noDataFoundException.getMessage(),
                HttpStatus.NO_CONTENT,
                ZonedDateTime.now());
        return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RejectedTransactionException.class)
    public ResponseEntity<Object> handleRejectedTransactionException(RejectedTransactionException rejectedTransactionException){
        ExceptionPayload payload = new ExceptionPayload(
                rejectedTransactionException.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        List<String> list = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult()
                .getFieldErrors()) {
            String defaultMessage = fieldError.getDefaultMessage();
            list.add(defaultMessage);
        }
        ExceptionPayload payload = new ExceptionPayload(list.toString(),HttpStatus.BAD_REQUEST,ZonedDateTime.now());


        return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
}
}
