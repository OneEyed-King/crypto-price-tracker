package com.mm.ct.state_store_service.utils;

import com.mm.ct.state_store_service.domain.MarketPriceEvent;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

public class Constant {

    public static final String STORE_NAME = "latest-price-store";

}
