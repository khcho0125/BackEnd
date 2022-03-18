package com.elasticsearch.Domain.Dto;

import com.elasticsearch.Domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String name;
    private String description;

    private UserResponseDto() {
    }

    public UserResponseDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getId(), user.getBasicProfile().getName(), user.getBasicProfile().getDescription());
    }
}
