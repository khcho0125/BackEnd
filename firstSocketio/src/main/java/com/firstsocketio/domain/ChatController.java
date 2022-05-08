package com.firstsocketio.domain;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private SocketIONamespace namespace;

    public ChatController(SocketIOServer server) {
        namespace = server.addNamespace("/chat");
        namespace.addEventListener("send", ChatMessage.class, ((client, data, ackSender) -> {
            client.sendEvent("getMessage", data);
        }));
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @OnEvent(SocketProP)
}
