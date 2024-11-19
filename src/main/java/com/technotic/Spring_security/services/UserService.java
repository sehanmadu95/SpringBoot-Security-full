package com.technotic.Spring_security.services;

import com.technotic.Spring_security.model.Users;
import com.technotic.Spring_security.repositories.MyUserDetailsRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final MyUserDetailsRepository myUserDetailsRepository;
    private  BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);

    public UserService(MyUserDetailsRepository myUserDetailsRepository) {
        this.myUserDetailsRepository = myUserDetailsRepository;

    }

    public Users createUser(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
      return   myUserDetailsRepository.save(users);
    }
}
