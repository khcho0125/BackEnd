package com.query.entity.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberVO2 {

    private final String name;
    private final String email;
    private final Long id;
    private final Long money;
    private final MemberSub follower;

    @QueryProjection
    public MemberVO2(String name, String email, Long id, Long money, MemberSub follower) {
        this.email = email;
        this.name = name;
        this.id = id;
        this.money = money;
        this.follower = follower;
    }
}
