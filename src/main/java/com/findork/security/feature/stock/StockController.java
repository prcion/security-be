package com.findork.security.feature.stock;

import com.findork.security.feature.stock.mapper.StockMapper;
import com.findork.security.feature.symbolAPI.StockApi;
import com.findork.security.feature.symbolAPI.StockApiService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/stock")
public class StockController {
    private final StockApiService stockApiService;
    private final StockService stockService;
    private final StockMapper stockMapper;
//
//    @GetMapping
//    public void get() {
//        stockApiService.createStocks();
//    }
//
    @GetMapping
    public ResponseEntity<?> getAllStocks(Pageable pageable) {
        var stocksPage = stockService.getAllStocks(pageable);
        return ResponseEntity.ok(new PageImpl<>(stockMapper.fromListStockToListStockDto(stocksPage.getContent()), pageable,stocksPage.getTotalElements()));
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<?> getStockById(@PathVariable("stockId") Long stockId) {
        return ResponseEntity.ok(stockMapper.fromStockToStockDto(stockService.getById(stockId)));
    }

}
