package com.elasticsearch.Domain.Repo;

import com.elasticsearch.Domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserSearchRepository extends ElasticsearchRepository<User, Long>, CustomUserSearchRepository {

    List<User> findByBasicProfile_NameContains(String name);
}
