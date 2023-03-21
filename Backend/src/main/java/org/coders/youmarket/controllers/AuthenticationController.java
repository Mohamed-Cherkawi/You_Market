package org.coders.youmarket.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.coders.youmarket.services.dtos.auth.AuthenticationRequestDto;
import org.coders.youmarket.services.dtos.auth.RegisterRequestDto;
import org.coders.youmarket.services.implementations.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor @Slf4j
@RequestMapping("/api/authenticate")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDto request) {
        return service.register(request);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequestDto request) {
        return service.authenticate(request);
    }
}