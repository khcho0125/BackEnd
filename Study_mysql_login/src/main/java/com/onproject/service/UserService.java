package com.onproject.service;

import com.onproject.Repository.UserRepository;
import com.onproject.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void joinUser(Member me) {
        me.setRole("Member");
        userRepository.save(me);
    }

    public Member loginmember(String id, String pw) {
        return userRepository.selectUserInfo(id,pw);
    }

}
