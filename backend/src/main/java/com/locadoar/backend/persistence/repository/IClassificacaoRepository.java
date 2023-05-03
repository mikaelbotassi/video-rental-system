package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Classificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassificacaoRepository extends JpaRepository<Classificacao, Integer> {
}
