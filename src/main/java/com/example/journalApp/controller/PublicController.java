package com.example.journalApp.controller;

import com.example.journalApp.Service.UserService;
import com.example.journalApp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void createUser(@RequestBody UserEntity userEntity) {
        userService.saveNewEntry(userEntity);
    }
}
