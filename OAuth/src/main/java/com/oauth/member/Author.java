package com.oauth.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Author {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

}
