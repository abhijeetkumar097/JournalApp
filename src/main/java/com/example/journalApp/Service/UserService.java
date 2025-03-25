package com.example.journalApp.Service;

import com.example.journalApp.entity.JournalEntity;
import com.example.journalApp.entity.UserEntity;
import com.example.journalApp.repository.JournalEntryRepo;
import com.example.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void saveEntry(UserEntity userEntity) {
        userRepo.save(userEntity);
    }

    public List<?> getAll() {
        return userRepo.findAll();
    }

    public void deleteById(ObjectId objectId) {
        userRepo.deleteById(objectId);
    }

    public Optional<?> findById(ObjectId objectId) {
        return userRepo.findById(objectId);
    }

    public UserEntity findByUsername(String username) {
        return userRepo.findByUsername(username);
    }


}
