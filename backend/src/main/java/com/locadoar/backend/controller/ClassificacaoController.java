package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Classificacao;
import com.locadoar.backend.persistence.repository.IClassificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/classificacao")
public class ClassificacaoController extends BaseController<Classificacao, IClassificacaoRepository> {

    @Autowired
    public ClassificacaoController( IClassificacaoRepository repository) {
        super(Classificacao.class, repository);
    }
}
