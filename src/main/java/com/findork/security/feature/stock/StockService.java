package com.findork.security.feature.stock;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StockService {
    private final StockRepository stockRepository;

    public Page<Stock> getAllStocks(Pageable pageable) {
        return stockRepository.findAllPaginated(pageable);
    }

    public Stock getById(Long stockId) {
        return stockRepository.getOne(stockId);
    }
}
