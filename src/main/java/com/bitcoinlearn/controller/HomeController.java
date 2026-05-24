package com.bitcoinlearn.controller;

import com.bitcoinlearn.service.BitcoinPriceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BitcoinPriceService priceService;

    public HomeController(BitcoinPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("priceUSD", priceService.getCurrentPriceUSD());
        model.addAttribute("priceBRL", priceService.getCurrentPriceBRL());
        model.addAttribute("change24h", priceService.getChange24h());
        return "index";
    }

    @GetMapping("/preco")
    public String preco(Model model) {
        model.addAttribute("priceUSD", priceService.getCurrentPriceUSD());
        model.addAttribute("priceBRL", priceService.getCurrentPriceBRL());
        model.addAttribute("change24h", priceService.getChange24h());
        return "fragments/preco :: preco";
    }
}
