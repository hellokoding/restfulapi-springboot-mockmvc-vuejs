package com.hellokoding.restfulapi.service;

import com.hellokoding.restfulapi.StockData;
import com.hellokoding.restfulapi.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {
    public Map<Long, Stock> findAll() {
        return StockData.INSTANCE.getStocks();
    }

    public Optional<Stock> findStockById(Long id) {
        return Optional.ofNullable(StockData.INSTANCE.getStocks().getOrDefault(id, null));
    }

    public Stock saveStock(Stock stock) {
        StockData.INSTANCE.getStocks().put(stock.getId(), stock);
        return stock;
    }
}
