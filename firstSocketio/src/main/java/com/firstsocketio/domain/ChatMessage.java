package com.firstsocketio.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatMessage {

    private String content;
    private String username;

}
