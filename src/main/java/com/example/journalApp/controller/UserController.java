package com.example.journalApp.controller;

import com.example.journalApp.Service.JournalEntryService;
import com.example.journalApp.Service.UserService;
import com.example.journalApp.entity.JournalEntity;
import com.example.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/view")
    public List<?> getAll() {
        return userService.getAll();
    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserEntity userEntity) {
        userService.saveEntry(userEntity);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<?>  updateUser(@PathVariable String username,@RequestBody UserEntity userEntity) {
        UserEntity userInDb = userService.findByUsername(username);
        if(userInDb != null) {
            userInDb.setUsername(userEntity.getUsername());
            userInDb.setPassword(userEntity.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
