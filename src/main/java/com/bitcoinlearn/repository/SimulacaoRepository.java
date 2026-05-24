package com.bitcoinlearn.repository;

import com.bitcoinlearn.model.Simulacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SimulacaoRepository extends MongoRepository<Simulacao, String> {
}
