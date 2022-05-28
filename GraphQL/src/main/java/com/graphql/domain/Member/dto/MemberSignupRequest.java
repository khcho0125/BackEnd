package com.graphql.domain.Member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignupRequest {

    private String memberId;
    private String password;
}
