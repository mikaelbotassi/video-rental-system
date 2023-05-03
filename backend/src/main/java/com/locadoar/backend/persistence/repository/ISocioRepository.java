package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISocioRepository extends JpaRepository<Socio, Integer> {

    List<Socio> findSocioByIsAtivo(boolean isAtivo);
}
