package com.hellokoding.restfulapi.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public class Stock {
    private static final AtomicLong count = new AtomicLong(0);
    private Long id;

    @NotNull
    @Size(min = 2, max = 32)
    private String name;

    @NotNull
    @DecimalMin("0")
    private BigDecimal currentPrice;

    private Long lastUpdate;

    public Stock() {
        this.id = count.incrementAndGet();
        this.lastUpdate = System.currentTimeMillis();
    }

    public Stock(String name, BigDecimal currentPrice) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (!id.equals(stock.id)) return false;
        if (name != null ? !name.equals(stock.name) : stock.name != null) return false;
        if (currentPrice != null ? !currentPrice.equals(stock.currentPrice) : stock.currentPrice != null) return false;
        return lastUpdate != null ? lastUpdate.equals(stock.lastUpdate) : stock.lastUpdate == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    public Stock clone() {
        Stock s = new Stock();
        s.setId(this.id);
        s.setName(this.name);
        s.setLastUpdate(this.getLastUpdate());
        return s;
    }

}
