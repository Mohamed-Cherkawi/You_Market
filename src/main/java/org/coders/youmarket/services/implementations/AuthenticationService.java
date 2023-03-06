package org.coders.youmarket.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.enums.AvailabilityStateEnum;
import org.coders.youmarket.repositories.UserRepository;
import org.coders.youmarket.services.dtos.auth.AuthenticationRequestDto;
import org.coders.youmarket.services.dtos.auth.RegisterRequestDto;
import org.coders.youmarket.services.dtos.auth.TokenRequestResponse;
import org.coders.youmarket.services.interfaces.AuthenticationServiceInterface;
import org.coders.youmarket.services.interfaces.RoleServiceInterface;
import org.coders.youmarket.services.interfaces.UserServiceInterface;
import org.coders.youmarket.services.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service @RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements AuthenticationServiceInterface {
    private final UserRepository userRepository;
    private final UserServiceInterface userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleServiceInterface roleService;

    @Override
    public TokenRequestResponse register(RegisterRequestDto request) {
        boolean isUsernameExists = userService.isUsernameAlreadyExists(request.getUsername());

        if(isUsernameExists)
            return null;

        AppUser user = AppUser.builder()
                .uuid(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .status(AvailabilityStateEnum.ONLINE)
                .role(roleService.findRoleByName(request.getRole()))
                .build();

        try {
            user = userService.saveUser(user);
            log.info("A New user was created successfully {}",user);
        }catch (Exception e){
            return null;
        }

        String jwtToken = jwtService.generateToken(user);

        return getAuthResponse(jwtToken);
    }

    @Override
    public Object authenticate(AuthenticationRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch(AuthenticationException e){
            e.printStackTrace();
            return "400";
        }

        AppUser user = userService.findUserByUsername(request.getUsername());
        if(user.getStatus().equals(AvailabilityStateEnum.BANNED))
            return "403";

        String jwtToken = jwtService.generateToken(user);

        return getAuthResponse(jwtToken);
    }
    private TokenRequestResponse getAuthResponse(String token) {
        return TokenRequestResponse.builder()
                .token(token)
                .build();
    }
}