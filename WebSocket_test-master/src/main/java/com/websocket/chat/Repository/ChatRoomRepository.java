package com.websocket.chat.Repository;

import com.fasterxml.jackson.core.io.CharTypes;
import com.websocket.chat.Entity.ChatRoom;
import com.websocket.chat.Service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Repository
public class ChatRoomRepository {

    private final RedisMessageListenerContainer container;
    private final RedisSubscriber redisSubscriber;

    private static final String CHAT_ROOMS = "CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, ChatRoom> opsHashChatRoom; // redis
    private Map<String, ChannelTopic> chatRoomMap;

    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash(); // hash 형태로 채팅방 저장
        chatRoomMap = new LinkedHashMap<>(); // roomId와 Topic 저장
    }

    public List<ChatRoom> findAllRoom() {
        return opsHashChatRoom.values(CHAT_ROOMS);
    }

    public ChatRoom findRoomById(String id) {
        return opsHashChatRoom.get(CHAT_ROOMS, id);
    }

    public ChatRoom createChatRoom(String name) { // 채팅방 만들 때
        ChatRoom chatRoom = ChatRoom.create(name);
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom); // redis 저장
        return chatRoom;
    }

    public void enterChatRoom(String roomId) { // 채팅방에 들어올 때
        ChannelTopic topic = chatRoomMap.get(roomId);
        if(topic == null) { // topic 이 처음 일 때
            topic = new ChannelTopic(roomId);
            container.addMessageListener(redisSubscriber, topic);
            chatRoomMap.put(roomId, topic);
        }
    }

    public ChannelTopic getTopic(String roomId) {
        return chatRoomMap.get(roomId);
    }
}
