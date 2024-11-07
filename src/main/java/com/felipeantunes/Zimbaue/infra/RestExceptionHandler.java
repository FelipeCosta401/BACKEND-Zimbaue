package com.felipeantunes.Zimbaue.infra;

import com.felipeantunes.Zimbaue.service.exceptions.BadRequestException;
import com.felipeantunes.Zimbaue.service.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionMessage> NotFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionMessage.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<ExceptionMessage> BadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ExceptionMessage> BadRequest(Exception ex){

        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder().message(ex.getMessage()).build());
    }
}
