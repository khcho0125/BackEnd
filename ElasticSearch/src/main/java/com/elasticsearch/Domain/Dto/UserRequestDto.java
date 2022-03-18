package com.elasticsearch.Domain.Dto;

import lombok.Getter;

@Getter
public class UserRequestDto {

    private String name;
    private String description;

    private UserRequestDto() {
    }

    public UserRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
