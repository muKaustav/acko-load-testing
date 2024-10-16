package com.acko.userprofile.service;

import com.acko.userprofile.model.UserData;
import com.acko.userprofile.repository.elasticsearch.UserProfileElasticsearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchService {

    private final UserProfileElasticsearchRepository userProfileElasticsearchRepository;

    @Autowired
    public ElasticsearchService(UserProfileElasticsearchRepository userProfileElasticsearchRepository) {
        this.userProfileElasticsearchRepository = userProfileElasticsearchRepository;
    }

    public UserData createUserProfile(UserData userData) {
        return userProfileElasticsearchRepository.save(userData);
    }

    public UserData getUserProfile(int userId) {
        return userProfileElasticsearchRepository.findByUserProfilePersonalProfileUserId(userId);
    }

    public UserData updateUserProfile(UserData userData) {
        return userProfileElasticsearchRepository.save(userData);
    }

    public void deleteUserProfile(String id) {
        userProfileElasticsearchRepository.deleteById(id);
    }

    public Iterable<UserData> getAllUserProfiles() {
        return userProfileElasticsearchRepository.findAll();
    }
}