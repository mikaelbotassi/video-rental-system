package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Diretor;
import com.locadoar.backend.persistence.repository.IDiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/diretor")
public class DiretorController extends BaseController<Diretor, IDiretorRepository> {

    @Autowired
    public DiretorController(IDiretorRepository repository) {
        super(Diretor.class, repository);
    }
}
