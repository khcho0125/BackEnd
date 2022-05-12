package com.firstsocketio.global.socketio;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SocketIoConfig {

    private final SocketIoProperties properties;
    private final ExceptionListener onException = new ExceptionListener() {
        @Override
        public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
            log.error("Error : {}", e.getMessage());
        }

        @Override
        public void onDisconnectException(Exception e, SocketIOClient client) {
            log.error("Disconnect Error : {}", e.getMessage());
            log.error("Session : {}", client.getSessionId());
        }

        @Override
        public void onConnectException(Exception e, SocketIOClient client) {
            log.error("Connect Error : {}", e.getMessage());
            log.error("Session : {}", client.getSessionId());
        }

        @Override
        public void onPingException(Exception e, SocketIOClient client) {
            log.error("Ping Error : {}", e.getMessage());
            log.error("Session : {}", client.getSessionId());
        }

        @Override
        public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
            log.error("ChannelPipeline Error : {}", e.getMessage());

            return false;
        }
    };

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        SocketConfig socketConfig = new SocketConfig();
        config.setExceptionListener(onException);
        config.setSocketConfig(socketConfig);
        config.setHostname(properties.getHost());
        config.setPort(properties.getPort());
        config.setBossThreads(1);
        config.setWorkerThreads(100);
        return new SocketIOServer(config);
    }
}
