package com.technotic.Spring_security.services;

import com.technotic.Spring_security.model.UserPrincipal;
import com.technotic.Spring_security.model.Users;
import com.technotic.Spring_security.repositories.MyUserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserDetailsRepository myUserDetailsRepository;

    public MyUserDetailsService(MyUserDetailsRepository myUserDetailsRepository) {
        this.myUserDetailsRepository = myUserDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=myUserDetailsRepository.findByUsername(username);
        if (user==null){
            log.info("MyUserDetailsService::loadUserByUsername --> User not found in database");
            throw  new UsernameNotFoundException("User not found in database");
        }
        log.info("MyUserDetailsService::loadUserByUsername --> User found in database");
        return new UserPrincipal(user);
    }
}
