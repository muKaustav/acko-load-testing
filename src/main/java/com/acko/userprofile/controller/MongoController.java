package com.acko.userprofile.controller;

import com.acko.userprofile.model.UserData;
import com.acko.userprofile.service.IMongoService;
import com.acko.userprofile.exception.CustomException;
import com.acko.userprofile.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mongodb")
public class MongoController {

    private final IMongoService mongoService;

    @Autowired
    public MongoController(IMongoService mongoService) {
        this.mongoService = mongoService;
    }

    @PostMapping("/user_profile")
    public ResponseEntity<ApiResponse<Map<String, Integer>>> createUserProfile(@RequestBody UserData userData) {
        UserData createdProfile = mongoService.addUserProfile(userData);
        Map<String, Integer> responseData = Map.of("userId", createdProfile.getUserProfile().getPersonalProfile().getUserId());
        ApiResponse<Map<String, Integer>> response = new ApiResponse<>(HttpStatus.CREATED, "User profile created successfully", responseData);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user_profile/{userId}")
    public ResponseEntity<ApiResponse<UserData>> getUserProfile(@PathVariable Integer userId) {
        UserData userProfile = mongoService.getUserProfile(userId);
        if (userProfile != null) {
            ApiResponse<UserData> response = new ApiResponse<>(HttpStatus.OK, "User profile retrieved successfully", userProfile);
            return ResponseEntity.ok(response);
        } else {
            throw new CustomException("User profile not found", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/user_profile/{userId}")
    public ResponseEntity<ApiResponse<Map<String, Integer>>> patchUserProfile(@PathVariable Integer userId, @RequestBody Map<String, Object> updates) {
        UserData updatedProfile = mongoService.patchUserProfile(userId, updates);
        Map<String, Integer> responseData = Map.of("userId", updatedProfile.getUserProfile().getPersonalProfile().getUserId());
        ApiResponse<Map<String, Integer>> response = new ApiResponse<>(HttpStatus.OK, "User profile updated successfully", responseData);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user_profile/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUserProfile(@PathVariable Integer userId) {
        mongoService.removeUserProfile(userId);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK, "User profile deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user_profiles")
    public ResponseEntity<ApiResponse<Iterable<UserData>>> getAllUserProfiles() {
        Iterable<UserData> userProfiles = mongoService.getAllUserProfiles();
        ApiResponse<Iterable<UserData>> response = new ApiResponse<>(HttpStatus.OK, "User profiles retrieved successfully", userProfiles);
        return ResponseEntity.ok(response);
    }
}