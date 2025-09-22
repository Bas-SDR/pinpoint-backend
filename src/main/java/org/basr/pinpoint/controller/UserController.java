package org.basr.pinpoint.controller;

import org.basr.pinpoint.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
