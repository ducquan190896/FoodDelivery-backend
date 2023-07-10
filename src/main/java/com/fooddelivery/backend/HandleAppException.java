package com.fooddelivery.backend;

import java.util.Arrays;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fooddelivery.backend.Exception.BadResultException;
import com.fooddelivery.backend.Exception.EntityExistingException;
import com.fooddelivery.backend.Exception.EntityNotFoundException;
import com.fooddelivery.backend.Exception.ErrorResponse;

@ControllerAdvice
public class HandleAppException {
    
    @ExceptionHandler({EntityNotFoundException.class, EntityExistingException.class, BadResultException.class}) 
    public ResponseEntity<Object> handlEntityException(RuntimeException ex) {
        ErrorResponse err = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlingArgumentException(MethodArgumentNotValidException ex) {
        
        ErrorResponse err = new ErrorResponse(Arrays.asList(ex.getMessage())); 
        System.out.println(err);
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
        
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handlingDataIntegrityException(DataIntegrityViolationException ex) {
       
       ErrorResponse err = new ErrorResponse(Arrays.asList(ex.getMessage())); 
       System.out.println(err);
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
        
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handlingEntityException(EmptyResultDataAccessException ex) {
        
        ErrorResponse err = new ErrorResponse(Arrays.asList(ex.getMessage())); 
        System.out.println(err);
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
        
    }
}
