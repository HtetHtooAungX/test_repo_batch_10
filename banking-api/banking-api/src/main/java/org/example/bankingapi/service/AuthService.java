package org.example.bankingapi.service;

import org.example.bankingapi.dao.RoleDao;
import org.example.bankingapi.dao.UserDao;
import org.example.bankingapi.dto.LoginDto;
import org.example.bankingapi.dto.RegisterDto;
import org.example.bankingapi.entity.Role;
import org.example.bankingapi.entity.User;
import org.example.bankingapi.entity.Wallet;
import org.example.bankingapi.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserDao userDao;
    private final AuthenticationManager authenticationManager;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthService(UserDao userDao, AuthenticationManager authenticationManager, RoleDao roleDao, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userDao = userDao;
        this.authenticationManager = authenticationManager;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String login(LoginDto loginDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        );
        Authentication authenticated = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        return jwtProvider.generateToken(authenticated);
    }

    public String register(RegisterDto registerDto) {
        if (userDao.existsByUsername(registerDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Username Already Exist");
        } else if (userDao.existsByEmail(registerDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Email Already Exist");
        }
        Role role = roleDao.findByName("ROLE_USER").get();
        User user = new User(
                registerDto.getName(),
                registerDto.getUsername(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword())
        );
        user.addRole(role);
        Wallet wallet = new Wallet();
        wallet.setAmount(500);
        user.addWallet(wallet);
        return userDao.save(user).getUsername() + " has successfully registered";
    }
}
