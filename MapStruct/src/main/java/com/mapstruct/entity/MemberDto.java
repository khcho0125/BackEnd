package com.mapstruct.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {

    private Long id;
    private String name;
    private Long age;
}
