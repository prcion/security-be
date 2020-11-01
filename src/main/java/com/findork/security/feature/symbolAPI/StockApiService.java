package com.findork.security.feature.symbolAPI;

import com.findork.security.feature.exchange.Exchange;
import com.findork.security.feature.exchange.ExchangeRepository;
import com.findork.security.feature.industry.Industry;
import com.findork.security.feature.industry.IndustryRepository;
import com.findork.security.feature.stock.Stock;
import com.findork.security.feature.stock.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockApiService {
    @Autowired
    private StockApi stockApi;
    @Autowired
    private SymbolApi symbolApi;
    @Autowired
    private ExchangeRepository exchangeRepository;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private StockRepository stockRepository;

    public void createStocks() {
        stockApi.getStocks().forEach(stockApi -> {
            var symbolApiResponse = symbolApi.getSymbol(stockApi.getSymbol()).get(0);
            if (symbolApiResponse.getIndustry() != null) {
                var exchange = findOrCreateExchange(stockApi.getExchange());
                var industry = findOrCreateIndustry(symbolApiResponse.getIndustry());

                Stock stock = Stock.builder()
                        .name(stockApi.getName())
                        .symbol(stockApi.getSymbol())
                        .logo(symbolApiResponse.getImage())
                        .currency(symbolApiResponse.getCurrency())
                        .country(symbolApiResponse.getCountry() == null ? "" : symbolApiResponse.getCountry())
                        .exchange(exchange)
                        .industry(industry)
                        .build();

                stockRepository.save(stock);
            }
        });
    }

    public Industry findOrCreateIndustry(String name) {
        var industryOptional = industryRepository.findByName(name);
        if (industryOptional.isEmpty()) {
            var industry = new Industry(name);
            return industryRepository.save(Industry.builder().name(name).build());
        }
        return industryOptional.get();
    }

    public Exchange findOrCreateExchange(String name) {
        var exchangeOptional = exchangeRepository.findByName(name);
        if (exchangeOptional.isEmpty()) {
            Exchange exchange = new Exchange(name);
            return exchangeRepository.save(Exchange.builder().name(name).build());
        }
        return exchangeOptional.get();
    }
}
