package com.query.entity.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberSub {

    private final Long id;
    private final String name;
    private final String email;
    private final Long money;

    @QueryProjection
    public MemberSub(Long id, String name, String email, Long money) {
        this.id = id;
        this.email = email;
        this.money = money;
        this.name = name;
    }
}
