package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.services.dtos.user.ProfileUpdateRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceInterface {
    List<AppUser> getAllUsers();
    AppUser saveUser(AppUser user);
    AppUser findUserByUsername(String username);
    AppUser findUserByReference(String reference);
    boolean isUsernameAlreadyExists(String username);
    ResponseEntity<Object> updateUserProfileInfos(ProfileUpdateRequest profileUpdateRequest);
    ResponseEntity<Object> getUserProfileInfosByReference(String reference);
}