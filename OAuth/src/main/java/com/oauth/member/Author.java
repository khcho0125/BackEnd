package com.oauth.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Getter
public enum Author {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    public Collection<? extends GrantedAuthority> getAuthor() {
        return Collections.singleton(new SimpleGrantedAuthority(value));
    }
}
