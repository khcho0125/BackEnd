package com.elasticsearch.Domain.Dto;

import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String name;
    private String description;

    public UserResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static UserResponse from(UserResponseDto userResponseDto) {
        return new UserResponse(
                userResponseDto.getId(),
                userResponseDto.getName(),
                userResponseDto.getDescription()
        );
    }
}
