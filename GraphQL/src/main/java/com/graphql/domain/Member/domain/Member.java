package com.graphql.domain.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_sn")
    private Long memberSn;

    @Column(name = "member_id", nullable = false, length = 100)
    private String memberId;

    @Column(name = "member_pw", nullable = false, length = 100)
    private String password;
}
