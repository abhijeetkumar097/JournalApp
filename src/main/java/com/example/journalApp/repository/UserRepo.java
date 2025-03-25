package com.example.journalApp.repository;

import com.example.journalApp.entity.JournalEntity;
import com.example.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUsername(String username); //findBy method name and field name
}
