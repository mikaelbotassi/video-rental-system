package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Ator;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAtorRepository extends JpaRepository<Ator, Integer> {
    List<Ator> findAtorByNome(String nome, Pageable pageable);
}
