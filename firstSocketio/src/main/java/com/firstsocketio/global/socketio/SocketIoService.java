package com.firstsocketio.global.socketio;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocketIoService {

    public static final Map<String, SocketIOClient> connectMap = new ConcurrentHashMap<>();

    private final SocketIOServer socketIOServer;

    @PostConstruct
    private void start() {
        socketIOServer.addConnectListener(client -> {
            String username = client.getHandshakeData().getSingleUrlParam("username");
            log.info("{}, {}", username, client.getSessionId());
            connectMap.put(username, client);
            client.set("username", username);
            log.info("Socket Connect User : {}", username);
        });
        socketIOServer.addDisconnectListener(client -> {
            String username = client.getHandshakeData().getSingleUrlParam("username");
            connectMap.remove(username);
            client.disconnect();
            log.info("Socket DisConnect User : {}", username);
        });

        socketIOServer.start();
    }

    @PreDestroy
    private void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
        }
    }
}
