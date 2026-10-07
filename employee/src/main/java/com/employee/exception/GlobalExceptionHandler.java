package com.employee.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
   //Exception handler methods

    @ExceptionHandler(Exception.class)
   public ResponseEntity<String> handleException(Exception ex){

        return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
   }


}
