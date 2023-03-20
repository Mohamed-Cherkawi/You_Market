package org.coders.youmarket.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.coders.youmarket.entities.Address;
import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.repositories.AddressRepository;
import org.coders.youmarket.repositories.UserRepository;
import org.coders.youmarket.services.dtos.response.ResponseHandler;
import org.coders.youmarket.services.dtos.user.ProfileUpdateRequest;
import org.coders.youmarket.services.interfaces.UserServiceInterface;
import org.coders.youmarket.util.EntityMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j @Service @RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override @Transactional
    public AppUser saveUser(AppUser user) {
       return userRepository.save(user);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public AppUser findUserByReference(String reference) {
        return userRepository.findByReference(reference).orElse(null);
    }

    @Override
    public boolean isUsernameAlreadyExists(String username) {
        return findUserByUsername(username) != null;
    }

    @Override @Transactional
    public ResponseEntity<Object> updateUserProfileInfos(ProfileUpdateRequest profileUpdateRequest) {
        AppUser user = findUserByReference(profileUpdateRequest.getUserReference());
        if(user == null){
            log.info("The provided reference : {} does not even exists as user reference in db", profileUpdateRequest.getUserReference());
            return ResponseHandler.generateResponse("The provided reference "+ profileUpdateRequest.getUserReference() +" does not even exists as user reference in db", HttpStatus.BAD_REQUEST);
        }
        Address userAddress = addressRepository.findById(user.getAddress().getId()).orElseThrow();

        userAddress.setTitle(profileUpdateRequest.getAddress().getTitle());
        userAddress.setDescription(profileUpdateRequest.getAddress().getDescription());

        user.setUsername(profileUpdateRequest.getUsername());
        user.setName(profileUpdateRequest.getName());
        user.setPhone(profileUpdateRequest.getPhone());
        user.setAddress(userAddress);
        user.setProfilePhoto(profileUpdateRequest.getProfilePhoto());

        user.setUpdatedAt(LocalDateTime.now());

        return ResponseHandler.generateResponse("Profile Has Been Saved Successfully",HttpStatus.OK,EntityMapping.userToProfilePreviewResponse(user));
    }
}