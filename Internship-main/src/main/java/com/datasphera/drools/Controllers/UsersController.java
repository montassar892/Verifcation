package com.datasphera.drools.Controllers;

import java.util.List;

import com.datasphera.drools.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api")
public class UsersController {

    private final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("Users")
    public boolean addUser(@RequestBody String name) {

        userService.addUser(name);
        return true;
    }

    @GetMapping("ShowUsers")
    public List<String> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("test")
    public boolean verifyUser(@RequestBody String user) {
        log.info(user);
        return (userService.testUser(user));

    }

}
