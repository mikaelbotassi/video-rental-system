package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDependenteRepository extends JpaRepository<Dependente, Integer> {

    List<Dependente> findDependenteByIsAtivo(boolean isAtivo);
}
