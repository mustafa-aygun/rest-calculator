package com.main.restcalculator.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An example modified exception handler class
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles missing parameter request
     * @param ex exception parameter
     * @param request request parameter
     * @return example response if it is not preferred to respond in a generic way
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("status", ex.getStatusCode());
        body.put("error", ex.getMessage());
        body.put("path", request.getRequestURI());
        return new ResponseEntity<>(body, ex.getStatusCode());
    }

    /**
     * Handles response status exceptions in a custom way
     * @param ex exception parameter
     * @param request request parameter
     * @return example response if it is not preferred to respond in a generic way
     */
    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("status", ex.getStatusCode());
        body.put("error", ex.getReason());
        body.put("path", request.getRequestURI());
        return new ResponseEntity<>(body, ex.getStatusCode());
    }
}