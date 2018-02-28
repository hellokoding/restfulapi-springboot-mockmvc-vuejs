package com.hellokoding.restfulapi.api;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hellokoding.restfulapi.model.Stock;
import com.hellokoding.restfulapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class StockAPI {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final StockService stockService;

    @Autowired
    public StockAPI(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/api/v1/stocks")
    public ResponseEntity<Map<Long, Stock>> findAll() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @PostMapping("/api/v1/stocks")
    public ResponseEntity createANewStock(@Valid @RequestBody Stock stock) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
//        }

        return ResponseEntity.ok(stockService.saveStock(stock));
    }

    @GetMapping("/api/v1/stocks/{stockId}")
    public ResponseEntity<Stock> findById(@PathVariable Long stockId) {
        Optional<Stock> stockOptional = stockService.findStockById(stockId);
        if (!stockOptional.isPresent()) {
            logger.severe("StockId " + stockId + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stockOptional.get());
    }

    @PutMapping("/api/v1/stocks/{stockId}")
    public ResponseEntity<Stock> updatePriceOfAStock(@PathVariable Long stockId, BigDecimal currentPrice) {
        Optional<Stock> stockOptional = stockService.findStockById(stockId);
        if (!stockOptional.isPresent()) {
            logger.severe("StockId " + stockId + " is not existed");
            ResponseEntity.badRequest().build();
        }

        Stock stock = stockOptional.get();
        stock.setCurrentPrice(currentPrice);

        return ResponseEntity.ok(stockService.saveStock(stock));
    }
}
