package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Titulo;
import com.locadoar.backend.persistence.repository.ITituloRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/titulo")
public class TituloController extends BaseController<Titulo, ITituloRepository> {

    public TituloController(ITituloRepository repository) {
        super(Titulo.class, repository);
    }
}
