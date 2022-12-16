package com.iqbalfa.electronic.controller;

import com.iqbalfa.electronic.exception.EntityExistException;
import com.iqbalfa.electronic.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(EntityExistException.class)
    ResponseEntity<ErrorResponse> handleEntityExistViolationException(EntityExistException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X03", exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(
            Exception exception
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("X06", exception.getMessage()));
    }
//    @ExceptionHandler(UnauthorizedException.class)
//    ResponseEntity<String> handleUnauthorizedException(UnauthorizedException unauthorizedException){
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body("Unauthorized");
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception exception){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
//    }


}
