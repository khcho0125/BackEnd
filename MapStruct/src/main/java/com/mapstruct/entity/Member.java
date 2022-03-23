package com.mapstruct.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private Long id;
    private String name;
    private Long age;
    private String email;
}
