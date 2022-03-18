package com.elasticsearch.Domain.API;

import com.elasticsearch.Domain.Dto.UserRequestDto;
import com.elasticsearch.Domain.Dto.UserResponseDto;
import com.elasticsearch.Domain.Repo.UserRepository;
import com.elasticsearch.Domain.Repo.UserSearchRepository;
import com.elasticsearch.Domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserSearchRepository userSearchRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getName(), userRequestDto.getDescription());
        User savedUser = userRepository.save(user);
        userSearchRepository.save(user);
        return savedUser.getId();
    }

    public List<UserResponseDto> searchByName(String name, Pageable pageable) {
        return userSearchRepository.searchByName(name, pageable)
                .stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }
}
