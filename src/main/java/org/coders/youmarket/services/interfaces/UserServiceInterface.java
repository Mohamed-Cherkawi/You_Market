package org.coders.youmarket.services.interfaces;


import org.coders.youmarket.entities.AppUser;

import java.util.List;

public interface UserServiceInterface {
    List<AppUser> getAllUsers();
    AppUser saveUser(AppUser user);
    AppUser findUserByUsername(String username);
    boolean isUsernameAlreadyExists(String username);
}