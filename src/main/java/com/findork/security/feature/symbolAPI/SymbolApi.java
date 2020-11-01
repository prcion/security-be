package com.findork.security.feature.symbolAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "symbol-api", url = "https://financialmodelingprep.com/api/v3/profile?apikey=48d72eede885ee7e0fecaaa39a1ea281")
public interface SymbolApi {
    @GetMapping("/{symbol}")
    List<SymbolApiResponse> getSymbol(@PathVariable("symbol") String symbol);
}
