package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.services.dtos.auth.AuthenticationRequestDto;
import org.coders.youmarket.services.dtos.auth.RegisterRequestDto;
import org.coders.youmarket.services.dtos.auth.TokenRequestResponse;

public interface AuthenticationServiceInterface {
    TokenRequestResponse register(RegisterRequestDto request);
    Object authenticate(AuthenticationRequestDto request);
}