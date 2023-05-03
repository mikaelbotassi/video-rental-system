package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITituloRepository extends JpaRepository<Titulo, Integer> {
}
