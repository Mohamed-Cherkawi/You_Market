package org.coders.youmarket.services.interfaces;

import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.services.dtos.user.ProfileUpdateRequest;

import java.util.List;

public interface UserServiceInterface {
    List<AppUser> getAllUsers();
    AppUser saveUser(AppUser user);
    AppUser findUserByUsername(String username);
    AppUser findUserByReference(String reference);
    boolean isUsernameAlreadyExists(String username);
    AppUser updateUserProfileInfos(ProfileUpdateRequest profileUpdateRequest);
}