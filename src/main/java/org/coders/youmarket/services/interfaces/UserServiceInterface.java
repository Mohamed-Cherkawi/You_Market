package org.coders.youmarket.services.interfaces;


import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.enums.RoleEnum;

import java.util.List;

public interface UserServiceInterface {
    List<AppUser> getAllUsers();
    AppUser saveUser(AppUser user);
    AppUser findUserByUsername(String username);
    AppUser findUserByRole(RoleEnum role);
    boolean isUsernameAlreadyExists(String username);
}