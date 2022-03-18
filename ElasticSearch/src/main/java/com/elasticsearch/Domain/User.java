package com.elasticsearch.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

@Getter
@Setter
@Document(indexName = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private BasicProfile basicProfile;

    protected User() {
    }

    public User(String name) {
        this(name, null);
    }

    public User(String name, String description) {
        this(null, new BasicProfile(name, description));
    }

    @PersistenceConstructor
    public User(Long id, BasicProfile basicProfile) {
        this.id = id;
        this.basicProfile = basicProfile;
    }

}