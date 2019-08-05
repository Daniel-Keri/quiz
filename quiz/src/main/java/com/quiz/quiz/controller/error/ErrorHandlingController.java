package com.quiz.quiz.controller.error;

import com.quiz.quiz.dto.error.ExceptionResponse;
import com.quiz.quiz.errorHandling.exceptions.CustomException;
import com.quiz.quiz.errorHandling.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(EntityNotFoundException e) throws Exception{

        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> badRequestException(CustomException e) throws Exception{

        ExceptionResponse exceptionResponse=new ExceptionResponse();
        exceptionResponse.setCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setDescription(e.getMessage());

        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> internalServerErrorException(Exception e) throws Exception{

    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    exceptionResponse.setDescription(e.getMessage());

    return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
