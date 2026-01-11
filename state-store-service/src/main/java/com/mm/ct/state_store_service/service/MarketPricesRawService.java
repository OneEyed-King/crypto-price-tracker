package com.mm.ct.state_store_service.service;

import com.mm.ct.state_store_service.domain.MarketPriceEvent;
import com.mm.ct.state_store_service.utils.Constant;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MarketPricesRawService {

    private final StreamsBuilderFactoryBean factoryBean;

    public MarketPricesRawService(StreamsBuilderFactoryBean factoryBean) {
        this.factoryBean = factoryBean;
    }



    public MarketPriceEvent getCurrentPrice(String symbol) {
        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, MarketPriceEvent> store = kafkaStreams
                .store(StoreQueryParameters.fromNameAndType(Constant.STORE_NAME, QueryableStoreTypes.keyValueStore()));
        System.out.println(store.get(symbol));
        return store.get(symbol);

    }
}
