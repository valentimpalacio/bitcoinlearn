package com.bitcoinlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BitcoinLearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(BitcoinLearnApplication.class, args);
    }
}
