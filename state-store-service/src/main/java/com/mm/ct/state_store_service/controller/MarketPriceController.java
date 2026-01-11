package com.mm.ct.state_store_service.controller;

import com.mm.ct.state_store_service.domain.MarketPriceEvent;
import com.mm.ct.state_store_service.service.MarketPricesRawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crypto-ticker")
public class MarketPriceController {

    @Autowired
    private MarketPricesRawService marketPricesRawService;

    @GetMapping("/get-price")
    public ResponseEntity<MarketPriceEvent> getMarketPrice(@RequestParam String symbol) {
        if(symbol == null || symbol.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(marketPricesRawService.getCurrentPrice(symbol), HttpStatus.OK);
    }
}
