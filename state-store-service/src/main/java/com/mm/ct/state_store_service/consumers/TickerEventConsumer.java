package com.mm.ct.state_store_service.consumers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TickerEventConsumer {

//    @Value("${spring.kafka.topic}")
//    private String topic;
//
//    @KafkaListener(topics = "market.prices.raw")
//    public void tickerEventConsumer(String message){
//        System.out.println(message);
//
//    }
}
