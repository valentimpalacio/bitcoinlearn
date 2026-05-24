package com.bitcoinlearn.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "simulacoes")
public class Simulacao {
    @Id
    private String id;
    private String nome;
    private double saldoInicial;
    private double saldoBRL;
    private double saldoBTC;
    private List<Transacao> transacoes;
    private LocalDateTime criadaEm;

    public Simulacao() {}

    public Simulacao(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldoInicial = saldoInicial;
        this.saldoBRL = saldoInicial;
        this.saldoBTC = 0;
        this.transacoes = new ArrayList<>();
        this.criadaEm = LocalDateTime.now();
    }

    public static class Transacao {
        private LocalDateTime data;
        private String tipo;
        private double quantidadeBTC;
        private double valorBRL;
        private double precoBTC;

        public Transacao() {}

        public Transacao(LocalDateTime data, String tipo, double quantidadeBTC, double valorBRL, double precoBTC) {
            this.data = data;
            this.tipo = tipo;
            this.quantidadeBTC = quantidadeBTC;
            this.valorBRL = valorBRL;
            this.precoBTC = precoBTC;
        }

        public LocalDateTime getData() { return data; }
        public void setData(LocalDateTime data) { this.data = data; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public double getQuantidadeBTC() { return quantidadeBTC; }
        public void setQuantidadeBTC(double quantidadeBTC) { this.quantidadeBTC = quantidadeBTC; }
        public double getValorBRL() { return valorBRL; }
        public void setValorBRL(double valorBRL) { this.valorBRL = valorBRL; }
        public double getPrecoBTC() { return precoBTC; }
        public void setPrecoBTC(double precoBTC) { this.precoBTC = precoBTC; }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(double saldoInicial) { this.saldoInicial = saldoInicial; }
    public double getSaldoBRL() { return saldoBRL; }
    public void setSaldoBRL(double saldoBRL) { this.saldoBRL = saldoBRL; }
    public double getSaldoBTC() { return saldoBTC; }
    public void setSaldoBTC(double saldoBTC) { this.saldoBTC = saldoBTC; }
    public List<Transacao> getTransacoes() { return transacoes; }
    public void setTransacoes(List<Transacao> transacoes) { this.transacoes = transacoes; }
    public LocalDateTime getCriadaEm() { return criadaEm; }
    public void setCriadaEm(LocalDateTime criadaEm) { this.criadaEm = criadaEm; }
}
