package com.devsuperior.hruser.resources;

import com.devsuperior.hruser.entities.User;
import com.devsuperior.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
    return ResponseEntity.ok(userRepository.findById(id).get());
    }

    @GetMapping("/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
    return ResponseEntity.ok(userRepository.findByEmail(email));
    }
}
