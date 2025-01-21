package org.example.foodie.security;

import org.example.foodie.dao.CustomerDao;
import org.example.foodie.entity.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final CustomerDao customerDao;

    public CustomUserDetailService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao.findByUsername(username)
                .map(SecurityCustomer::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
