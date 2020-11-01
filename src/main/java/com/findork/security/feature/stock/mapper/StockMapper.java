package com.findork.security.feature.stock.mapper;

import com.findork.security.feature.account.mapper.UserMapperDecorator;
import com.findork.security.feature.stock.Stock;
import com.findork.security.feature.stock.StockDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
@DecoratedWith(StockMapperDecorator.class)
public interface StockMapper {
    StockDto fromStockToStockDto(Stock stock);
    List<StockDto> fromListStockToListStockDto(List<Stock> stocks);
}
