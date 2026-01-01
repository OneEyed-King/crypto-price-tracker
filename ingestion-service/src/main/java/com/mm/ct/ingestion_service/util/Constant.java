package com.mm.ct.ingestion_service.util;

import lombok.Data;

import java.util.List;
@Data
public class Constant {

    public static final List<String> cryptoSymbols = List.of(
            "BTCUSDT", "ETHUSDT", "SOLUSDT", "BNBUSDT", "XRPUSDT",
            "ADAUSDT", "DOGEUSDT", "MATICUSDT", "DOTUSDT", "AVAXUSDT",

            "BTCUSD", "ETHUSD", "BTCUSDC", "ETHUSDC", "SOLUSDC",
            "XRPUSDC", "ADAUSDC", "BNBUSDC", "DOGEUSDC", "MATICUSDC",

            "ETHBTC", "BNBBTC", "SOLBTC", "XRPBTC", "ADABTC",
            "AVAXBTC", "DOGEBTC", "DOTBTC", "MATICBTC", "LINKBTC",
//
            "ETHBUSD", "BTCBUSD", "SOLBUSD", "ADABUSD", "XRPP-USDT", // alternative notation
            "LTCUSDT", "LINKUSDT", "TRXUSDT", "UNIUSDT", "SHIBUSDT",
//
            "ETHETH2-USDT", "FTMUSDT", "NEARUSDT", "ATOMUSDT", "ALGOUSDT",
            "ICPUSDT", "APEUSDT", "SUIUSDT", "XTZUSDT", "FILUSDT"
    );

}
