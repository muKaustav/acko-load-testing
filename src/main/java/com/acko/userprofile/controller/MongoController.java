package com.acko.userprofile.controller;

import com.acko.userprofile.model.UserData;
import com.acko.userprofile.service.IMongoService;
import com.acko.userprofile.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mongodb")
public class MongoController {

    private final IMongoService mongoService;

    @Autowired
    public MongoController(IMongoService mongoService) {
        this.mongoService = mongoService;
    }

    @PostMapping("/user_profile")
    public ResponseEntity<UserData> createUserProfile(@RequestBody UserData userData) {
        UserData createdProfile = mongoService.addUserProfile(userData);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping("/user_profile/{userId}")
    public ResponseEntity<UserData> getUserProfile(@PathVariable Integer userId) {
        UserData userProfile = mongoService.getUserProfile(userId);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user_profile")
    public ResponseEntity<UserData> updateUserProfile(@RequestBody UserData userData) {
        try {
            UserData updatedProfile = mongoService.modifyUserProfile(userData);
            return ResponseEntity.ok(updatedProfile);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user_profile/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable String id) {
        try {
            mongoService.removeUserProfile(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user_profiles")
    public ResponseEntity<Iterable<UserData>> getAllUserProfiles() {
        Iterable<UserData> userProfiles = mongoService.getAllUserProfiles();
        return ResponseEntity.ok(userProfiles);
    }
}