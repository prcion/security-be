package com.findork.security.feature.stock.mapper;

import com.findork.security.feature.stock.Stock;
import com.findork.security.feature.stock.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public abstract class StockMapperDecorator implements StockMapper {
    @Autowired
    private StockMapper stockMapper;

    @Override
    public StockDto fromStockToStockDto(Stock stock) {
        StockDto stockDto = stockMapper.fromStockToStockDto(stock);
        stockDto.setExchangeName(stock.getExchange().getName());
        stockDto.setIndustryName(stock.getIndustry().getName());
        return stockDto;
    }

    public List<StockDto> fromListStockToListStockDto(List<Stock> stocks) {
        return stocks.stream().map(this::fromStockToStockDto).collect(Collectors.toList());
    }
}
