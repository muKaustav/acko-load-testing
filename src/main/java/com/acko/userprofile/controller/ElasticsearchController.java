package com.acko.userprofile.controller;

import com.acko.userprofile.model.UserData;
import com.acko.userprofile.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @PostMapping("/user_profile")
    public ResponseEntity<UserData> createUserProfile(@RequestBody UserData userData) {
        return ResponseEntity.ok(elasticsearchService.createUserProfile(userData));
    }

    @GetMapping("/user_profile/{userId}")
    public ResponseEntity<UserData> getUserProfile(@PathVariable int userId) {
        UserData userData = elasticsearchService.getUserProfile(userId);
        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user_profiles")
    public ResponseEntity<Iterable<UserData>> getAllUserProfiles() {
        return ResponseEntity.ok(elasticsearchService.getAllUserProfiles());
    }
}