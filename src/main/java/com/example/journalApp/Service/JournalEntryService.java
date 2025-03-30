package com.example.journalApp.Service;

import com.example.journalApp.entity.JournalEntity;
import com.example.journalApp.entity.UserEntity;
import com.example.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    public void add(JournalEntity journalEntity, String username) {
        UserEntity user = userService.findByUsername(username);
        journalEntity.setDate(LocalDateTime.now());
        JournalEntity saved = journalEntryRepo.save(journalEntity);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public void add(JournalEntity journalEntity) {
        journalEntryRepo.save(journalEntity);
    }

    public List<JournalEntity> view() {
        return journalEntryRepo.findAll();
    }

    public void deleteById(ObjectId objectId, String username) throws Exception{
        UserEntity user = userService.findByUsername(username);
        boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(objectId));
        if(removed) {
            journalEntryRepo.deleteById(objectId);
            userService.saveEntry(user);
        }
        else {
            throw new Exception("No such Id");
        }
    }

    public Optional<JournalEntity> findById(ObjectId objectId) {
        return journalEntryRepo.findById(objectId);
    }

    public List<?> findByUsername(String username) {
        return userService.findByUsername(username).getJournalEntries();
    }

}
