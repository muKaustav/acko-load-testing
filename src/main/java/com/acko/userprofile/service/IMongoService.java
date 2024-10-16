package com.acko.userprofile.service;

import com.acko.userprofile.model.UserData;
import com.acko.userprofile.exception.CustomException;

import java.util.List;
import java.util.Map;

public interface IMongoService {
    List<UserData> findAll();
    UserData save(UserData userData);
    UserData addUserProfile(UserData userData);
    UserData getUserProfile(Integer userId);
    UserData patchUserProfile(Integer userId, Map<String, Object> updates);
    void removeUserProfile(Integer userId);
    List<UserData> getAllUserProfiles();
}