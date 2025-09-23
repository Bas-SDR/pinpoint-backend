package org.basr.pinpoint.controller;

import org.basr.pinpoint.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    //fake db
    Map<Long, User> userMap = new HashMap<>();

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        this.userMap.put(user.getId(), user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<Long, User>> getAllUsers() {
        return ResponseEntity.ok(this.userMap);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        if (this.userMap.containsKey(id)) {
            return ResponseEntity.ok(this.userMap.get(id));
        }
        else {
            return ResponseEntity.notFound().build();
            }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (this.userMap.containsKey(id)) {
            this.userMap.put(id, user);
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
