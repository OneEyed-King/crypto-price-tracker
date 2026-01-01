package com.mm.ct.ingestion_service.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiniTickerDTO {

    private String s;   // symbol
    private String c;   // close price
    private long E;     // event time

}
