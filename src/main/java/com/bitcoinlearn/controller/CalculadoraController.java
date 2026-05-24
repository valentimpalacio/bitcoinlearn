package com.bitcoinlearn.controller;

import com.bitcoinlearn.service.BitcoinPriceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculadora")
public class CalculadoraController {

    private final BitcoinPriceService priceService;

    public CalculadoraController(BitcoinPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public String calculadora(Model model) {
        model.addAttribute("priceUSD", priceService.getCurrentPriceUSD());
        model.addAttribute("priceBRL", priceService.getCurrentPriceBRL());
        return "calculadora";
    }
}
