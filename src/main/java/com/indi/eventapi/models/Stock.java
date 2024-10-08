package com.indi.eventapi.models;

import com.indi.eventapi.dto.StockDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    private String company;

    private String code;

    private Float adjClose;

    private Float open;

    private Float close;

    private Float high;

    private Float low;

    private Integer volume;

    public static Stock toStock(StockDto stockDto) {
        return Stock.builder()
                .company(Company.codeMap.get(stockDto.getName()))
                .code(stockDto.getName())
                .adjClose(stockDto.getAdjClose())
                .open(stockDto.getOpen())
                .close(stockDto.getClose())
                .high(stockDto.getHigh())
                .low(stockDto.getLow())
                .volume(stockDto.getVolume())
                .build();
    }
}
