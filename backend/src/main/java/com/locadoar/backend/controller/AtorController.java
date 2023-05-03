package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Ator;
import com.locadoar.backend.persistence.repository.IAtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ator")
public class AtorController extends BaseController<Ator, IAtorRepository>{
    @Autowired
    public AtorController(IAtorRepository repository) {
        super(Ator.class, repository);
    }
}
