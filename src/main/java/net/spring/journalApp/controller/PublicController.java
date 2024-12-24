package net.spring.journalApp.controller;

import net.spring.journalApp.entity.User;
import net.spring.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}
