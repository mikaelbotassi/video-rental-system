package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocacaoRepository extends JpaRepository<Locacao, Integer> {
}
