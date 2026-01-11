package com.mm.ct.state_store_service.domain;

import lombok.Data;

@Data
public class MarketPriceEvent {

    private String s;   // symbol
    private String c;   // close price
    private long E;     // event time

}
