package com.mm.ct.ingestion_service.websocketClient;

import com.mm.ct.ingestion_service.config.WebSocketConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;

@Component
public class BinanceWebSocketClient {

    private static final String BINANCE_WS_URL =
            "wss://stream.binance.com:9443/ws/!miniTicker@arr";

    private final WebSocketHandler webSocketHandler;

    public BinanceWebSocketClient(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @PostConstruct
    public void connect() {
        try {
            StandardWebSocketClient client =
                    WebSocketConfig.createClient();



            client.execute(
                    webSocketHandler,
                    new WebSocketHttpHeaders(),
                    URI.create(BINANCE_WS_URL)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}