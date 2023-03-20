package org.coders.youmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.services.dtos.user.ProfileUpdateRequest;
import org.coders.youmarket.services.interfaces.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserServiceInterface userServiceInterface;

    @PutMapping("/updating")
    public ResponseEntity<Object> updateUserProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest){
        return userServiceInterface.updateUserProfileInfos(profileUpdateRequest);
    }
}
