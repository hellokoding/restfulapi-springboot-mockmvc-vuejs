package com.hellokoding.restfulapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Application implements CommandLineRunner{
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("Initialize data...");
        StockData.INSTANCE.initData();
    }
}
