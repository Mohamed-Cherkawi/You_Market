package org.coders.youmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.services.dtos.user.ProfileUpdateRequest;
import org.coders.youmarket.services.interfaces.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserServiceInterface userService;

    @GetMapping("/fetching/single/{userReference}")
    public ResponseEntity<Object> getUserProfileInfosByItsReferenceApi(@PathVariable("userReference") String userReference){
        return userService.getUserProfileInfosByReference(userReference);
    }
    @PutMapping("/updating")
    public ResponseEntity<Object> updateUserProfileApi(@RequestBody ProfileUpdateRequest profileUpdateRequest){
        return userService.updateUserProfileInfos(profileUpdateRequest);
    }
}
