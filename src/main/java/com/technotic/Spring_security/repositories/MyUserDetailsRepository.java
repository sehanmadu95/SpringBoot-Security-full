package com.technotic.Spring_security.repositories;

import com.technotic.Spring_security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyUserDetailsRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
