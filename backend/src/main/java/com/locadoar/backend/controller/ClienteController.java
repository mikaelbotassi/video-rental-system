package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Cliente;
import com.locadoar.backend.persistence.repository.IClienteRepository;
import com.locadoar.backend.usecase.DesativarCliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cliente")
public class ClienteController extends BaseController<Cliente, IClienteRepository> {

    public ClienteController(IClienteRepository repository) {
        super(Cliente.class, repository);
    }

    @Override
    protected void delete(Integer id) {
        new DesativarCliente(id, repository).execute();
    }

    @GetMapping("listarAtivos")
    public List<Cliente> listarAtivos() {
        return repository.findClienteByIsAtivo(true);
    }
}
