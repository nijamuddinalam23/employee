package com.employee.exception;


import com.employee.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {
   //Exception handler methods

    @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorDto> handleException(
           Exception ex,
           WebRequest request) {

        ErrorDto errorDto = new ErrorDto(
               new Date(),
                ex.getMessage(),
                request.getDescription(false)

        );

        return new ResponseEntity<>(errorDto,HttpStatus.INTERNAL_SERVER_ERROR);
   }
   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ErrorDto> resourceNotFound(
           ResourceNotFoundException ex,
           WebRequest request
   ){
       ErrorDto errorDto = new ErrorDto(
               new Date(),
               ex.getMessage(),
               request.getDescription(false)

       );

       return new ResponseEntity<>(errorDto,HttpStatus.INTERNAL_SERVER_ERROR);

   }


}
