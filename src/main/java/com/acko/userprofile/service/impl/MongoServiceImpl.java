package com.acko.userprofile.service.impl;

import com.acko.userprofile.model.UserData;
import com.acko.userprofile.repository.mongo.UserProfileMongoRepository;
import com.acko.userprofile.service.IMongoService;
import com.acko.userprofile.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoServiceImpl implements IMongoService {

    @Autowired
    private UserProfileMongoRepository userProfileMongoRepository;

    @Override
    public List<UserData> findAll() {
        return userProfileMongoRepository.findAll();
    }

    @Override
    public UserData save(UserData userData) {
        return userProfileMongoRepository.save(userData);
    }

    @Override
    public UserData addUserProfile(UserData userData) {
        return save(userData);
    }

    @Override
    public void removeUserProfile(String id) {
        Optional<UserData> userDataOptional = userProfileMongoRepository.findById(id);
        if (userDataOptional.isPresent()) {
            UserData userData = userDataOptional.get();
            userProfileMongoRepository.delete(userData);
        } else {
            throw new NotFoundException("User profile with ID " + id + " not found.");
        }
    }

    @Override
    public UserData modifyUserProfile(UserData userData) {
        if (userProfileMongoRepository.existsById(userData.getId())) {
            return save(userData);
        } else {
            throw new NotFoundException("User profile with ID " + userData.getId() + " not found.");
        }
    }

    @Override
    public UserData getUserProfile(Integer userId) {
        return userProfileMongoRepository.findByUserProfilePersonalProfileUserId(userId);
    }

    @Override
    public Iterable<UserData> getAllUserProfiles() {
        return findAll();
    }
}