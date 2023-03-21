package org.coders.youmarket.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
    private static Map<String,Object> map = new LinkedHashMap<>();
    private static HttpHeaders responseHeaders = new HttpHeaders();

    private ResponseHandler() {

    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        putContentInMap(message,status,responseObj);

        return ResponseEntity.status(status).body(map);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status,Object responseObj ,HeaderKeyValueResponse header) {
        responseHeaders.set(header.getKey(),header.getValue());

        putContentInMap(message, status, responseObj);

        return ResponseEntity.status(status).headers(responseHeaders).body(map);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(message);
    }

    private static void putContentInMap(String message, HttpStatus status , Object responseObj){
        map.put("message",message);
        map.put("statusCode",status.value());
        map.put("statusName",status.name());
        map.put("data",responseObj);
    }
}