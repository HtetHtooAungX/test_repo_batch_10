package org.example.bankingapi.service;

import lombok.RequiredArgsConstructor;
import org.example.bankingapi.dao.UserDao;
import org.example.bankingapi.entity.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Secured("ROLE_ADMIN")
    public List<User> findAll() {
        return userDao.findAll();
    }
}
