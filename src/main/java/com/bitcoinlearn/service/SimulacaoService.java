package com.bitcoinlearn.service;

import com.bitcoinlearn.model.Simulacao;
import com.bitcoinlearn.model.Simulacao.Transacao;
import com.bitcoinlearn.repository.SimulacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SimulacaoService {

    private static final Logger log = LoggerFactory.getLogger(SimulacaoService.class);
    private final SimulacaoRepository repo;
    private final BitcoinPriceService priceService;

    public SimulacaoService(SimulacaoRepository repo, BitcoinPriceService priceService) {
        this.repo = repo;
        this.priceService = priceService;
    }

    public Simulacao criar(String nome, double saldoInicial) {
        Simulacao s = new Simulacao(nome, saldoInicial);
        return repo.save(s);
    }

    public Simulacao comprar(String id, double valorBRL) {
        Simulacao s = repo.findById(id).orElseThrow();
        double precoBTC = priceService.getCurrentPriceBRL();
        if (precoBTC <= 0) {
            log.warn("Compra rejeitada: preco BTC invalido ({}) para simulacao {}", precoBTC, id);
            return s;
        }
        if (s.getSaldoBRL() < valorBRL) {
            log.warn("Compra rejeitada: saldo insuficiente ({} < {}) para simulacao {}", s.getSaldoBRL(), valorBRL, id);
            return s;
        }
        double quantidade = valorBRL / precoBTC;
        s.setSaldoBRL(s.getSaldoBRL() - valorBRL);
        s.setSaldoBTC(s.getSaldoBTC() + quantidade);
        s.getTransacoes().add(new Transacao(LocalDateTime.now(), "COMPRA", quantidade, valorBRL, precoBTC));
        log.info("Compra realizada: {} BRL -> {} BTC @ {} na simulacao {}", valorBRL, quantidade, precoBTC, id);
        return repo.save(s);
    }

    public Simulacao vender(String id, double quantidadeBTC) {
        Simulacao s = repo.findById(id).orElseThrow();
        double precoBTC = priceService.getCurrentPriceBRL();
        if (precoBTC <= 0) {
            log.warn("Venda rejeitada: preco BTC invalido ({}) para simulacao {}", precoBTC, id);
            return s;
        }
        if (s.getSaldoBTC() < quantidadeBTC) {
            log.warn("Venda rejeitada: BTC insuficiente ({} < {}) para simulacao {}", s.getSaldoBTC(), quantidadeBTC, id);
            return s;
        }
        double valorBRL = quantidadeBTC * precoBTC;
        s.setSaldoBTC(s.getSaldoBTC() - quantidadeBTC);
        s.setSaldoBRL(s.getSaldoBRL() + valorBRL);
        s.getTransacoes().add(new Transacao(LocalDateTime.now(), "VENDA", quantidadeBTC, valorBRL, precoBTC));
        log.info("Venda realizada: {} BTC -> {} BRL @ {} na simulacao {}", quantidadeBTC, valorBRL, precoBTC, id);
        return repo.save(s);
    }

    public double calcularValorTotal(Simulacao s) {
        double precoBTC = priceService.getCurrentPriceBRL();
        return s.getSaldoBRL() + (s.getSaldoBTC() * precoBTC);
    }

    public List<Simulacao> listar() {
        return repo.findAll();
    }

    public Simulacao buscar(String id) {
        return repo.findById(id).orElseThrow();
    }

    public void deletar(String id) {
        repo.deleteById(id);
    }
}
