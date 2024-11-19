package com.technotic.Spring_security.services;

import com.technotic.Spring_security.model.Users;
import com.technotic.Spring_security.repositories.MyUserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final MyUserDetailsRepository myUserDetailsRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private  BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);

    public UserService(MyUserDetailsRepository myUserDetailsRepository, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.myUserDetailsRepository = myUserDetailsRepository;

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public Users createUser(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
      return   myUserDetailsRepository.save(users);
    }

    public String verifyUser(Users users) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));

        if (authenticate.isAuthenticated()){
            log.info("UserService::verifyUser--> login successfully");
            return jwtService.returnToken(users.getUsername());
        }
        else {
            log.info("UserService::verifyUser--> login unsuccessfully");
            return "Wrong crendital..";
        }
    }
}
