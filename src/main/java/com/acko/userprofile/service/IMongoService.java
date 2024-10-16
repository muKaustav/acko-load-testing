package com.acko.userprofile.service;

import com.acko.userprofile.model.UserData;

import java.util.List;

public interface IMongoService {
    List<UserData> findAll();

    UserData save(UserData userData);

    UserData addUserProfile(UserData userData);

    void removeUserProfile(String id);

    UserData modifyUserProfile(UserData userData);

    UserData getUserProfile(Integer userId);

    Iterable<UserData> getAllUserProfiles();
}