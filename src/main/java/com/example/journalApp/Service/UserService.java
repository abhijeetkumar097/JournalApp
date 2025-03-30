package com.example.journalApp.Service;

import com.example.journalApp.entity.UserEntity;
import com.example.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveNewEntry(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER"));
        userRepo.save(userEntity);
    }

    public void saveNewAdmin(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepo.save(userEntity);
    }

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

    public void deleteByUsername(String username) {
        userRepo.deleteByUsername(username);
    }


}
