package org.coders.youmarket.repositories;


import org.coders.youmarket.entities.Role;
import org.coders.youmarket.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(RoleEnum role);
}