package com.mm.ct.ingestion_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.WebSocketContainer;

@Configuration
public class WebSocketConfig {

    public static StandardWebSocketClient createClient() {
        WebSocketContainer container =
                ContainerProvider.getWebSocketContainer();

        // Increase buffer sizes (IMPORTANT)
        container.setDefaultMaxTextMessageBufferSize(1024 * 1024); // 1 MB
        container.setDefaultMaxBinaryMessageBufferSize(1024 * 1024);

        return new StandardWebSocketClient(container);
    }
}