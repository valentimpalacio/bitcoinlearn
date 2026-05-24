package com.bitcoinlearn.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class BitcoinPriceService {

    private double currentPriceUSD = 0;
    private double currentPriceBRL = 0;
    private double change24h = 0;

    @Scheduled(fixedRate = 60000)
    public void fetchPrice() {
        try {
            RestTemplate rt = new RestTemplate();
            Map<String, Map<String, Object>> data = rt.getForObject(
                "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=brl%2Cusd&include_24hr_change=true",
                Map.class
            );
            if (data != null && data.containsKey("bitcoin")) {
                Map<String, Object> btc = data.get("bitcoin");
                currentPriceUSD = ((Number) btc.getOrDefault("usd", 0)).doubleValue();
                currentPriceBRL = ((Number) btc.getOrDefault("brl", 0)).doubleValue();
                change24h = ((Number) btc.getOrDefault("usd_24h_change", 0)).doubleValue();
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar preco: " + e.getMessage());
        }
    }

    public double getCurrentPriceUSD() { return currentPriceUSD; }
    public double getCurrentPriceBRL() { return currentPriceBRL; }
    public double getChange24h() { return change24h; }
}
