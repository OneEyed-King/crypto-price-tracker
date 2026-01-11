package com.mm.ct.state_store_service.config;

import com.mm.ct.state_store_service.domain.MarketPriceEvent;
import com.mm.ct.state_store_service.domain.MarketPriceEventSerde;
import com.mm.ct.state_store_service.utils.Constant;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class MarketPricesRawTopology {

    @Value("${spring.kafka.topic}")
    private String topic;



    @Bean
    public KStream<String, MarketPriceEvent> kafkaStreams(StreamsBuilder builder) {

        KStream<String, MarketPriceEvent> kStream = builder.stream(topic,  Consumed.with(Serdes.String(), new MarketPriceEventSerde()));

        kStream.groupByKey()
                .reduce(((oldValue, newValue) -> newValue.getE() >= oldValue.getE() ? newValue : oldValue)
                        , Materialized.<String, MarketPriceEvent, KeyValueStore<Bytes, byte[]>>as(Constant.STORE_NAME)
                        .withKeySerde(Serdes.String())
                        .withValueSerde(new MarketPriceEventSerde()));

        return kStream;
    }
}
