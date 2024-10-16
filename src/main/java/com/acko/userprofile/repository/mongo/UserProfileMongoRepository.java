package com.acko.userprofile.repository.mongo;

import com.acko.userprofile.model.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserProfileMongoRepository extends MongoRepository<UserData, String> {
    @Query("{'userProfile.personalProfile.userId': ?0}")
    UserData findByUserProfilePersonalProfileUserId(Integer userId);
}