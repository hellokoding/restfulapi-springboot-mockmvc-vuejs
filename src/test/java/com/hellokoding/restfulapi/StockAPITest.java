package com.hellokoding.restfulapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellokoding.restfulapi.api.StockAPI;
import com.hellokoding.restfulapi.model.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StockAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockAPI stockAPI;

    @Test
    public void findAll() throws Exception {
        Map<Long, Stock> data = StockData.INSTANCE.getStocks();
        given(stockAPI.findAll()).willReturn(ResponseEntity.ok(data));

        this.mockMvc.perform(get("/api/v1/stocks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(data.size())));
    }

    @Test
    public void findById() throws Exception {
        Stock stock = new Stock("S" + System.currentTimeMillis(), new BigDecimal(System.currentTimeMillis()));
        given(stockAPI.findById(stock.getId())).willReturn(ResponseEntity.ok(stock));

        this.mockMvc.perform(get("/api/v1/stocks/" + stock.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(stock.getName()))
                .andExpect(jsonPath("$.currentPrice").value(stock.getCurrentPrice()));
    }

    @Test
    public void createANewStock() throws Exception {
        Stock stock = new Stock("S" + System.currentTimeMillis(), new BigDecimal( System.currentTimeMillis()));
        given(stockAPI.createANewStock(stock)).willReturn(ResponseEntity.ok(stock));

        this.mockMvc.perform(post("/api/v1/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(stock.getName()))
                .andExpect(jsonPath("currentPrice").value(stock.getCurrentPrice()));
    }

    @Test
    public void updatePriceOfAStock() throws Exception {
        Stock stock = new Stock("S" + System.currentTimeMillis(), new BigDecimal( System.currentTimeMillis()));
        BigDecimal updatingPrice = stock.getCurrentPrice().add(BigDecimal.ONE);
        Stock updatedStock = stock.clone();
        updatedStock.setCurrentPrice(updatingPrice);
        given(stockAPI.updatePriceOfAStock(stock.getId(), updatingPrice)).willReturn(ResponseEntity.ok(updatedStock));

        this.mockMvc.perform(put("/api/v1/stocks/" + stock.getId())
                .param("currentPrice", stock.getCurrentPrice().add(BigDecimal.ONE).toString())
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(stock.getName()))
                .andExpect(jsonPath("currentPrice").value(updatedStock.getCurrentPrice()));
    }

}
