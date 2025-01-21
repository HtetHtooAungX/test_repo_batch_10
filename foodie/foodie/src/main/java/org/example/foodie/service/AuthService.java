package org.example.foodie.service;

import org.example.foodie.dao.CustomerDao;
import org.example.foodie.dao.RoleDao;
import org.example.foodie.dto.LoginDto;
import org.example.foodie.dto.RegisterDto;
import org.example.foodie.entity.Customer;
import org.example.foodie.entity.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;
    private final AuthenticationManager authenticationManager;

    public AuthService(CustomerDao customerDao, PasswordEncoder passwordEncoder, RoleDao roleDao, AuthenticationManager authenticationManager) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public String register(RegisterDto registerDto) {
        Customer customer = new Customer(registerDto.getName(),registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()), registerDto.getEmail());
        Role role = roleDao.findByRoleName("ROLE_USER").get();
        customer.addRole(role);
        customerDao.save(customer);
        return customer.getId() + " has successfully registered";
    }

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "success login";
    }
}