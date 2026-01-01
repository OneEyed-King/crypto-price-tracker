package com.mm.ct.ingestion_service.websocketClient;

import com.mm.ct.ingestion_service.model.dto.MiniTickerDTO;
import com.mm.ct.ingestion_service.producer.TickerEventProducer;
import com.mm.ct.ingestion_service.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class BinanceWebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(BinanceWebSocketHandler.class);

    private final ObjectMapper objectMapper;

    private final TickerEventProducer tickerEventProducer;

    public BinanceWebSocketHandler(ObjectMapper objectMapper, TickerEventProducer tickerEventProducer) {
        this.objectMapper = objectMapper;
        this.tickerEventProducer = tickerEventProducer;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("Connected to Binance WebSocket");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
//        log.info("Received message: {}", message.getPayload());

        try {
            List<MiniTickerDTO> tickers =
                    objectMapper.readValue(
                            message.getPayload(),
                            new TypeReference<List<MiniTickerDTO>>() {}
                    );
           tickers.removeIf(symbol-> !Constant.cryptoSymbols.contains(symbol.getS()));
           log.info("Received tickers  =================== : " + tickers.size());
            for (MiniTickerDTO ticker : tickers) {
                tickerEventProducer.sendPriceTicks(ticker);
                // logs for development
//                log.info(
//                        "Symbol={}, Price={}, EventTime={}",
//                        ticker.getS(),
//                        ticker.getC(),
//                        ticker.getE()
//                );
            }

        } catch (Exception e) {
            log.error("Failed to parse message", e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        log.warn("WebSocket closed: {}", status);
    }
}
