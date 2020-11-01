package com.findork.security.feature.symbolAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "stock-api", url = "https://financialmodelingprep.com/api/v3/stock/list?apikey=48d72eede885ee7e0fecaaa39a1ea281")
public interface StockApi {
    @GetMapping
    List<StockResponse> getStocks();
}
