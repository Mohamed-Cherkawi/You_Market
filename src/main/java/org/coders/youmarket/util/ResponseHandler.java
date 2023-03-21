package org.coders.youmarket.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseHandler {

    private ResponseHandler() {

    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {

        return ResponseEntity.status(status).body(Map.of(
                "message", message,
                "statusCode", status.value(),
                "statusName" , status.name(),
                "data", responseObj)
        );

    }
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(message);
    }
}