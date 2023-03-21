package org.coders.youmarket.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
    private static Map<String,Object> map = new LinkedHashMap<>();

    private ResponseHandler() {

    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {

        map.put("message",message);
        map.put("statusCode",status.value());
        map.put("statusName",status.name());
        map.put("data",responseObj);

        return ResponseEntity.status(status).body(map);
    }
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(message);
    }
}