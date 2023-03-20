package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.services.dtos.auth.AuthenticationRequestDto;
import org.coders.youmarket.services.dtos.auth.RegisterRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationServiceInterface {
    ResponseEntity<Object> register(RegisterRequestDto request);
    ResponseEntity<Object> authenticate(AuthenticationRequestDto request);
}