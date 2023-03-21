package org.coders.youmarket.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.coders.youmarket.entities.Address;
import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.enums.AvailabilityStateEnum;
import org.coders.youmarket.services.dtos.auth.AuthenticationRequestDto;
import org.coders.youmarket.services.dtos.auth.RegisterRequestDto;
import org.coders.youmarket.services.dtos.auth.TokenRequestResponse;
import org.coders.youmarket.util.ResponseHandler;
import org.coders.youmarket.services.interfaces.AuthenticationServiceInterface;
import org.coders.youmarket.services.interfaces.UserServiceInterface;
import org.coders.youmarket.services.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service @RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements AuthenticationServiceInterface {
    private final UserServiceInterface userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Object> register(RegisterRequestDto request) {
        boolean isUsernameExists = userService.isUsernameAlreadyExists(request.getUsername());

        if(isUsernameExists)
            return ResponseHandler.generateResponse("This username is already taken try another one", HttpStatus.BAD_REQUEST);

        AppUser user = AppUser.builder()
                .reference(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .status(AvailabilityStateEnum.ONLINE)
                .address(
                        Address.builder()
                                .title(request.getAddress().getTitle())
                                .description(request.getAddress().getDescription())
                                .build()
                )
                .createdAt(LocalDateTime.now())
                .build();

        try {
            user = userService.saveUser(user);
            log.info("A New user was created successfully {}",user);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Something went wrong while saving the user , please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String jwtToken = jwtService.generateToken(user);

        return ResponseHandler.generateResponse("The user was successfully created",HttpStatus.CREATED, getAuthResponse(jwtToken));
    }

    @Override
    public ResponseEntity<Object> authenticate(AuthenticationRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch(AuthenticationException e){
            log.info("The following username {} does not exists !",request.getUsername());
            return ResponseHandler.generateResponse("Bad Credentials or the account is not even exists",HttpStatus.BAD_REQUEST);
        }

        AppUser user = userService.findUserByUsername(request.getUsername());
        if(user.getStatus().equals(AvailabilityStateEnum.BANNED))
            return ResponseHandler.generateResponse("You can't reach the application for now because the admin has banned you",HttpStatus.UNAUTHORIZED);

        String jwtToken = jwtService.generateToken(user);

        return ResponseHandler.generateResponse("Authentication Succeed",HttpStatus.OK,getAuthResponse(jwtToken));
    }
    private TokenRequestResponse getAuthResponse(String token) {
        return TokenRequestResponse.builder()
                .token(token)
                .build();
    }
}