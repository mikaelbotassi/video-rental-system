package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiretorRepository extends JpaRepository<Diretor, Integer> {
}
