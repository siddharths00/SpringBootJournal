package net.spring.journalApp.controller;

import net.spring.journalApp.entity.JournalEntry;
import net.spring.journalApp.entity.User;
import net.spring.journalApp.service.JournalEntryService;
import net.spring.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username) {
        User userInDB = userService.findByUsername(username);
        if(userInDB != null) {
            if(!user.getUsername().equals(""))
                userInDB.setUsername(user.getUsername());
            if(!user.getPassword().equals(""))
            userInDB.setPassword(user.getPassword());

            userService.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
