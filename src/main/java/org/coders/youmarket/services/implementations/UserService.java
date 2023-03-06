package org.coders.youmarket.services.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.coders.youmarket.entities.AppUser;
import org.coders.youmarket.enums.RoleEnum;
import org.coders.youmarket.repositories.RoleRepository;
import org.coders.youmarket.repositories.UserRepository;
import org.coders.youmarket.services.interfaces.UserServiceInterface;
import org.coders.youmarket.services.security.JwtService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

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
    public AppUser findUserByRole(RoleEnum role) {
        return userRepository.findByRole(
                roleRepository.findRoleByName(role)
                ).orElse(null);
    }

    @Override
    public boolean isUsernameAlreadyExists(String username) {
        return findUserByUsername(username) != null;
    }
}