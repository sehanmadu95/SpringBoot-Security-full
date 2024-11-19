package com.technotic.Spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  //To enable custome security and disable default security
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /*when we configure bean of securityFilterChain() and EnableWebsecurity default web security will be disbale*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(customizer -> customizer.disable()); //disble csrf
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated()); //Enable authenticated for all request.
        httpSecurity.formLogin(Customizer.withDefaults()); //Enabled the form loging
        httpSecurity.httpBasic(Customizer.withDefaults()); //Enable Rest APi Access like postman othwse its return only loging form
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        /*everytime ceating new session id, then issue with web logging redirect bcz of
        stateless, prevent that one remove default form logging*/

        return httpSecurity.build();
    }

    //In Memory Database
  /*  @Bean //disbale default system creating username password and customize with in memory data
    public UserDetailsService userDetailsService(){
        UserDetails user1= User
                .withDefaultPasswordEncoder()
                .username("madu")
                .password("1234")
                .build();

        UserDetails user2= User
                .withDefaultPasswordEncoder()
                .username("nipu")
                .password("12345")
                .build();



        return new InMemoryUserDetailsManager(user1,user2);
    }*/

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
      //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); ///default no passowrd encoder
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
