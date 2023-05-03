package com.locadoar.backend.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public abstract class BaseController<DOMAIN, REPOSITORY extends JpaRepository<DOMAIN, Integer>> {
    protected Class domainClass;
    protected REPOSITORY repository;

    public BaseController(Class domainClass, REPOSITORY repository) {
        this.domainClass = domainClass;
        this.repository = repository;
    }

    @GetMapping("{id}")
    protected Optional<DOMAIN> getById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @GetMapping("list")
    protected List<DOMAIN> list() {
        return repository.findAll();
    }

    @PostMapping("save")
    protected DOMAIN persist(@RequestBody DOMAIN body) {
        return repository.save(body);
    }

    @DeleteMapping("delete")
    protected void delete(@RequestParam Integer id) {
        repository.deleteById(id);
    }
}
