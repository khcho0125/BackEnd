package com.firstsocketio.domain;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
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
        namespace.addEventListener("userJoin", JoinMessage.class, onJoin());

        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    private DataListener<ChatMessage> onMessage() {
        return (client, data, ackSender) -> {
            log.info("message: {}\nuser: {}\nsessionId: {}", data.getMessage(), data.getUsername(), data.getSessionId());
            namespace.getBroadcastOperations().sendEvent("newMessage", data);
            messageRepository.save(data);
        };
    }

    private DataListener<JoinMessage> onJoin() {
        return (client, data, ackSender) -> {
            log.info("room : {}, user : {}", data.getRoomNum(), data.getUsername());
            client.joinRoom(data.getRoomNum());
            Integer online = namespace.getRoomOperations(data.getRoomNum()).getClients().size();
            namespace.getRoomOperations(data.getRoomNum()).sendEvent("count", online);
        };
    }
}
