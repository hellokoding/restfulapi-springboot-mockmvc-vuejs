package com.hellokoding.restfulapi;

import com.hellokoding.restfulapi.model.Stock;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum StockData {
    INSTANCE;

    private Map<Long, Stock> stocks;

    public void initData() {
        stocks = new HashMap<>();

        for (int i = 1; i <= 10; i++) {
            Stock newStock = new Stock("S" + i, new BigDecimal(i));
            stocks.put(newStock.getId(), newStock);
        }
    }

    public Map<Long, Stock> getStocks(){
        return stocks;
    }
}
