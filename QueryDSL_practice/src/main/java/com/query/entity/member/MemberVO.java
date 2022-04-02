package com.query.entity.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberVO {

    private final String name;
    private final String email;
    private final Long id;
    private final Long money;

    @QueryProjection
    public MemberVO(String name, String email, Long id, Long money) {
        this.email = email;
        this.name = name;
        this.id = id;
        this.money = money;
    }
}
