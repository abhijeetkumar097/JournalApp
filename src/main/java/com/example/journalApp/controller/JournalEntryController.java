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
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/view/{username}")
    public ResponseEntity<?> view(@PathVariable String username) {
        UserEntity user = userService.findByUsername(username);
        List<?> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{username}")
    public ResponseEntity<?> add(@RequestBody JournalEntity journalEntity, @PathVariable String username) {
        try {
            journalEntryService.add(journalEntity, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{username}/{id}")
    public ResponseEntity<?> delete(@PathVariable ObjectId id, @PathVariable String username)  {
        try {
            journalEntryService.deleteById(id, username);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable ObjectId id) {
        Optional<JournalEntity> object = journalEntryService.findById(id);
        if (object.isPresent()) {
            return new ResponseEntity<>(object.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<?> update(
            @PathVariable String username,
            @PathVariable ObjectId id,
            @RequestBody JournalEntity journalEntity
    ) {
        JournalEntity content = journalEntryService.findById(id).orElse(null);
        if(content != null) {
            content.setTitle(journalEntity.getTitle() != null && !journalEntity.getTitle().equals("") ? journalEntity.getTitle() : content.getTitle());
            content.setContent(journalEntity.getContent() != null && !journalEntity.getContent().equals("") ? journalEntity.getContent() : content.getContent());
            journalEntryService.add(content);
            return new ResponseEntity<>(content, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
