package com.oauth.member;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private final String name;
    private final String email;
    private final String picture;

    public SessionMember(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.picture = member.getPicture();
    }
}
