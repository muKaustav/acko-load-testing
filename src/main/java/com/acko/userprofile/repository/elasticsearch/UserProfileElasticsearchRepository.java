package com.acko.userprofile.repository.elasticsearch;

import com.acko.userprofile.model.UserData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserProfileElasticsearchRepository extends ElasticsearchRepository<UserData, String> {
    UserData findByUserProfilePersonalProfileUserId(int userId);
}