package com.locadoar.backend.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TITULO")
public class Titulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TITULO")
    private Integer id;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "ANO", length = 4)
    private String ano;

    @Column(name = "SINOPSE", length = 10000)
    private String sinopse;

    @NotNull
    @Column(name = "CATEGORIA", length = 200)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "ID_DIRETOR")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "ID_CLASSIFICACAO")
    private Classificacao classificacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ATOR_TITULO"
            , joinColumns = {
            @JoinColumn(name = "ID_TITULO")
        }
            , inverseJoinColumns = {
            @JoinColumn(name = "ID_ATOR")
        }
    )
    private Set<Ator> atores;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Set<Ator> getAtores() {
        return atores;
    }

    public void setAtores(Set<Ator> atores) {
        this.atores = atores;
    }
}
