package com.bitcoinlearn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class BitcoinPriceService {

    private static final Logger log = LoggerFactory.getLogger(BitcoinPriceService.class);
    private static final String URL = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=brl%2Cusd&include_24hr_change=true";

    private double currentPriceUSD = 0;
    private double currentPriceBRL = 0;
    private double change24h = 0;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(fixedRate = 60000)
    public void fetchPrice() {
        try {
            HttpClient client = HttpClient.newBuilder().connectTimeout(java.time.Duration.ofSeconds(10)).build();
            HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("User-Agent", "BitcoinLearn/1.0")
                .timeout(java.time.Duration.ofSeconds(10))
                .GET().build();
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() != 200) {
                log.warn("CoinGecko API retornou status {}", res.statusCode());
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Map<String, Object>> data = mapper.readValue(res.body(), Map.class);
            if (data != null && data.containsKey("bitcoin")) {
                Map<String, Object> btc = data.get("bitcoin");
                if (btc.containsKey("usd")) currentPriceUSD = ((Number) btc.get("usd")).doubleValue();
                if (btc.containsKey("brl")) currentPriceBRL = ((Number) btc.get("brl")).doubleValue();
                if (btc.containsKey("usd_24h_change")) change24h = ((Number) btc.get("usd_24h_change")).doubleValue();
                log.info("Preços atualizados - USD: {}, BRL: {}", currentPriceUSD, currentPriceBRL);
            }
        } catch (Exception e) {
            log.error("Erro ao buscar preco: {} - {}", e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public double getCurrentPriceUSD() { return currentPriceUSD; }
    public double getCurrentPriceBRL() { return currentPriceBRL; }
    public double getChange24h() { return change24h; }
}
