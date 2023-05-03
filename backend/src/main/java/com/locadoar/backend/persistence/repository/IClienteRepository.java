package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findClienteByIsAtivo(boolean ativo);
}
