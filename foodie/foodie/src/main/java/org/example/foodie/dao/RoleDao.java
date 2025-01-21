package org.example.foodie.dao;

import org.example.foodie.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);
}
