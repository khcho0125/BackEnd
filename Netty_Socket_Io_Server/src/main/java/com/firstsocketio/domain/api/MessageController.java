package com.firstsocketio.domain.api;

import com.firstsocketio.domain.MessageRepository;
import com.firstsocketio.domain.RoomRepository;
import com.firstsocketio.domain.entity.ChatMessage;
import com.firstsocketio.domain.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    @GetMapping("/messages/{roomCode}")
    public List<ChatMessage> findAll(@PathVariable("roomCode") String roomCode) {
        return messageRepository.findChatMessages(roomCode);
    }

    @GetMapping("/rooms")
    public List<ChatRoom> chatRooms() {
        return roomRepository.findAll();
    }

    @PostMapping("/room/create")
    public void createRoom(String roomName) {
        String roomCode = "";
        do {
            roomCode = randomCode();
        } while (roomRepository.existsByRoomName(roomCode));

        roomRepository.save(ChatRoom.builder()
                .roomName(roomName).roomCode(roomCode).build());
    }

    @DeleteMapping("/room/delete")
    public void deleteRoom(String roomCode) {
        roomRepository.deleteByRoomCode(roomCode);
        messageRepository.deleteByRoomCode(roomCode);
    }

    private String randomCode() {
        return new RandomString(4).toString();
    }
}
