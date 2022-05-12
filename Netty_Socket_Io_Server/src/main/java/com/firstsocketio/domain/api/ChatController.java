package com.firstsocketio.domain.api;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.firstsocketio.domain.MessageRepository;
import com.firstsocketio.domain.api.dto.EventMessage;
import com.firstsocketio.domain.entity.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChatController {

    private final SocketIONamespace namespace;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatController(SocketIOServer server, MessageRepository messageRepository) {
        namespace = server.addNamespace("/chat");
        namespace.addEventListener("send", ChatMessage.class, onMessage());
        namespace.addEventListener("userJoin", EventMessage.class, onJoin());
        namespace.addEventListener("userLeave", EventMessage.class, onLeave());
        namespace.addEventListener("userTyping", EventMessage.class, onTyping());
        namespace.addEventListener("userStopTyping", EventMessage.class, onStopTyping());

        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    private DataListener<ChatMessage> onMessage() {
        return (client, data, ackSender) -> {
            log.info("message: {}, user: {}, sessionId: {}", data.getMessage(), client.get("username"), data.getSessionId());
            namespace.getRoomOperations(data.getRoomCode()).sendEvent("newMessage", client, data);
            messageRepository.save(data);
        };
    }

    private DataListener<EventMessage> onJoin() {
        return (client, data, ackSender) -> {
            log.info("room:{}, user: {}, session: {}", data.getRoomCode(), client.get("username"), data.getSessionId());
            client.joinRoom(data.getRoomCode());
            Integer online = namespace.getRoomOperations(data.getRoomCode()).getClients().size();
            namespace.getRoomOperations(data.getRoomCode()).sendEvent("count", online);
        };
    }

    private DataListener<EventMessage> onTyping() {
        return (client, data, ackSender) -> {
            log.info("Typing, session: {}", data.getSessionId());
            namespace.getRoomOperations(data.getRoomCode()).sendEvent("userTyping", client, data);
        };
    }

    private DataListener<EventMessage> onStopTyping() {
        return (client, data, ackSender) -> {
            log.info("StopTyping, session: {}", data.getSessionId());
            namespace.getRoomOperations(data.getRoomCode()).sendEvent("userStopTyping", client, data);
        };
    }

    private DataListener<EventMessage> onLeave() {
        return (client, data, ackSender) -> {
            log.info("room: {}, user: {}", data.getRoomCode(), client.get("username"));
            client.leaveRoom(data.getRoomCode());
            Integer online = namespace.getRoomOperations(data.getRoomCode()).getClients().size();
            namespace.getRoomOperations(data.getRoomCode()).sendEvent("count", online);
        };
    }
}
