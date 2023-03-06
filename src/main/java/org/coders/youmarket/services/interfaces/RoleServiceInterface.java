package org.coders.youmarket.services.interfaces;


import org.coders.youmarket.entities.Role;
import org.coders.youmarket.enums.RoleEnum;

public interface RoleServiceInterface {
    Role findRoleByName(RoleEnum roleName);
}