package org.coders.youmarket.services.implementations;

import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.Role;
import org.coders.youmarket.enums.RoleEnum;
import org.coders.youmarket.repositories.RoleRepository;
import org.coders.youmarket.services.interfaces.RoleServiceInterface;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class RoleService implements RoleServiceInterface {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(RoleEnum roleName) {
        return roleRepository.findRoleByName(roleName);
    }
}