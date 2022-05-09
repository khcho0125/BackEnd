package com.firstsocketio.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;

    @GetMapping("/messages/{roomId}")
    public List<ChatMessage> findAll(@PathVariable("roomId") String roomNum) {
        return messageRepository.findChatMessages(roomNum);
    }
}
