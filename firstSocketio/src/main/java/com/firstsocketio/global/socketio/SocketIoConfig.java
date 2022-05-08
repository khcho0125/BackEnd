package com.firstsocketio.global.socketio;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SocketIoConfig {

    private final SocketIoProperties properties;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        SocketConfig socketConfig = new SocketConfig();
        config.setSocketConfig(socketConfig);
        config.setHostname(properties.getHost());
        config.setPort(properties.getPort());
        return new SocketIOServer(config);
    }
}
