package com.mm.ct.ingestion_service.producer;

import com.mm.ct.ingestion_service.model.dto.MiniTickerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class TickerEventProducer {

    @Value("${spring.kafka.topic}")
    private String topic;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TickerEventProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPriceTicks(MiniTickerDTO miniTickerDTO) {
        String value = null;
        try {
            value = objectMapper.writeValueAsString(miniTickerDTO);
        } catch (Exception e) {
            log.error("Error while serializing MiniTickerDTO", e);
            return;
        }
        var key = miniTickerDTO.getS();
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, value);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                handelFailure(ex, key);
            } else {
                handleSuccess(key);
            }
        });

    }

    private void handleSuccess(String key) {
        log.info("Successfully sent message to topic for: key={}", key);
    }

    private void handelFailure(Throwable ex, String key) {
        log.error("Unable to send message for: key={}", key ,ex);
    }

}
