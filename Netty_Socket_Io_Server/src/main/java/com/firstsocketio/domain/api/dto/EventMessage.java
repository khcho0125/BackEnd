package com.firstsocketio.domain.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventMessage {

    private String sessionId;
    private String username;
    private String roomCode;
}
