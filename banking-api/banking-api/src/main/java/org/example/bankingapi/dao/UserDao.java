package org.example.bankingapi.dao;

import org.example.bankingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    @Query("""
select u from User u where u.username like :key or u.email like :key
""")
    Optional<User> findByUsernameOrEmail(@Param("key") String usernameOrEmail);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
