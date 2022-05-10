package com.firstsocketio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChatRoom extends BaseEntity {

    @Id
    private String roomCode;

    private String roomName;
}
