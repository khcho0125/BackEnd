package com.onproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;

    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 400)
    private String password;

    @Column(length = 400, name = "name")
    private String name;

    private String Role;
}
