package com.example.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // make the exception handler global and available to all controller
public class StudentRestExceptionHandler {
    // Add an exception handler
//    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exp){

//        create a student error response
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exp.getMessage());
        response.setTimeStamp(System.currentTimeMillis());

//        return responseEntity
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // add another generic exception to handle exception like mismatch/invalid argument type
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){

        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        errorResponse.setMessage(exc.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
