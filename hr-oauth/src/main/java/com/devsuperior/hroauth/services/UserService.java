package com.devsuperior.hroauth.services;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email){
        Optional<User> user = Optional.ofNullable(userFeignClient.findByEmail(email).getBody());

        if (!user.isPresent()) {
            log.error("Email not found, "+email);
            throw new IllegalArgumentException("Email not found");
        }

        log.info("Email found: "+email);
        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userFeignClient.findByEmail(email).getBody());

        if (!user.isPresent()) {
            log.error("Email not found, "+email);
            throw new UsernameNotFoundException("Email not found");
        }

        log.info("Email found: "+email);
        return user.get();
    }
}
