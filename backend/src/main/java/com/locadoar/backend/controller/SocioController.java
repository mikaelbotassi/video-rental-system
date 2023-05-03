package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Socio;
import com.locadoar.backend.persistence.repository.ISocioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/socio")
public class SocioController extends BaseController<Socio, ISocioRepository> {
    public SocioController(ISocioRepository repository) {
        super(Socio.class, repository);
    }

    @GetMapping("listarAtivos")
    public List<Socio> listarAtivos() {
        return repository.findSocioByIsAtivo(true);
    }
}
