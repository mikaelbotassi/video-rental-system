package com.locadoar.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM")
    private Integer id;

    @Column(name = "numero_serie", unique = true)
    private String numeroSerie;

    @Column(name = "DT_AQUISICAO")
    private LocalDateTime dataAquisicao;

    @Column(name = "tipo_item")
    private String tipoItem;

    @ManyToOne
    @JoinColumn(name = "ID_TITULO")
    private Titulo titulo;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private Set<Locacao> locacaoSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer idItem) {
        this.id = idItem;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public LocalDateTime getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDateTime dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public Set<Locacao> getLocacaoSet() {
        return locacaoSet;
    }

    public void setLocacaoSet(Set<Locacao> locacaoSet) {
        this.locacaoSet = locacaoSet;
    }
}
