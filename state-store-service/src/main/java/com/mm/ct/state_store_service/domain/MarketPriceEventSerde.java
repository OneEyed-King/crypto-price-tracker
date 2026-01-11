package com.mm.ct.state_store_service.domain;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;


public class MarketPriceEventSerde extends Serdes.WrapperSerde<MarketPriceEvent> {
    public MarketPriceEventSerde() {
        super(new JacksonJsonSerializer<>(), new JacksonJsonDeserializer<>(MarketPriceEvent.class));
    }
}
