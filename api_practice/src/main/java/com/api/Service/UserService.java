package com.api.Service;

import com.api.Dto.UserDto;
import com.api.Entity.Authority;
import com.api.Entity.User;
import com.api.Repository.UserRepository;
import com.api.Util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signup(UserDto userDto) {
        if(userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) { // 이미 저장되어있는 정보인가 확인
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder() // 권한정보
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()  // 유저정보 builder
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user); // userRepository 에 save
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) { // username 을 기준으로 정보 가져옴
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() { // Security Context 에 저장된 username 의 정보를 가져옴
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
