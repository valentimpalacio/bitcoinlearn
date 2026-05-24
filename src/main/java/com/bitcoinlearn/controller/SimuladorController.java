package com.bitcoinlearn.controller;

import com.bitcoinlearn.model.Simulacao;
import com.bitcoinlearn.service.BitcoinPriceService;
import com.bitcoinlearn.service.SimulacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/simulador")
public class SimuladorController {

    private final SimulacaoService service;
    private final BitcoinPriceService priceService;

    public SimuladorController(SimulacaoService service, BitcoinPriceService priceService) {
        this.service = service;
        this.priceService = priceService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("simulacoes", service.listar());
        model.addAttribute("priceBRL", priceService.getCurrentPriceBRL());
        return "simulador";
    }

    @PostMapping("/criar")
    public String criar(@RequestParam String nome, @RequestParam double saldo) {
        service.criar(nome, saldo);
        return "redirect:/simulador";
    }

    @PostMapping("/{id}/comprar")
    public String comprar(@PathVariable String id, @RequestParam double valor) {
        service.comprar(id, valor);
        return "redirect:/simulador/" + id;
    }

    @PostMapping("/{id}/vender")
    public String vender(@PathVariable String id, @RequestParam double quantidade) {
        service.vender(id, quantidade);
        return "redirect:/simulador/" + id;
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable String id, Model model) {
        Simulacao s = service.buscar(id);
        model.addAttribute("simulacao", s);
        model.addAttribute("valorTotal", service.calcularValorTotal(s));
        model.addAttribute("priceBRL", priceService.getCurrentPriceBRL());
        return "simulador-detalhe";
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable String id) {
        service.deletar(id);
        return "redirect:/simulador";
    }
}
