package com.acko.userprofile.service.impl;

import com.acko.userprofile.exception.CustomException;
import com.acko.userprofile.model.UserData;
import com.acko.userprofile.repository.mongo.UserProfileMongoRepository;
import com.acko.userprofile.service.IMongoService;
import com.acko.userprofile.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MongoServiceImpl implements IMongoService {

    @Autowired
    private UserProfileMongoRepository userProfileMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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
    public void removeUserProfile(Integer userId) {
        UserData userData = userProfileMongoRepository.findByUserProfilePersonalProfileUserId(userId);
        if (userData != null) {
            userProfileMongoRepository.delete(userData);
        } else {
            throw new CustomException("User profile with userId " + userId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserData patchUserProfile(Integer userId, Map<String, Object> updates) throws NotFoundException {
        Query query = new Query(Criteria.where("userProfile.personalProfile.userId").is(userId));
        Update update = new Update();

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            update.set(key, value);
        }

        UserData updatedUserData = mongoTemplate.findAndModify(query, update, UserData.class);

        if (updatedUserData == null) {
            throw new NotFoundException("User profile with userId " + userId + " not found.");
        }

        return updatedUserData;
    }

    @Override
    public UserData getUserProfile(Integer userId) {
        return userProfileMongoRepository.findByUserProfilePersonalProfileUserId(userId);
    }

    @Override
    public List<UserData> getAllUserProfiles() {
        return findAll();
    }
}