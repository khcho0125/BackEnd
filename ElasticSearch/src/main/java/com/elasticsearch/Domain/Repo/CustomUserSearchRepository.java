package com.elasticsearch.Domain.Repo;

import com.elasticsearch.Domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomUserSearchRepository {

    List<User> searchByName(String name, Pageable pageable);
}