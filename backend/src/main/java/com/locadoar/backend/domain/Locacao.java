package com.locadoar.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Fetch;

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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOCACAO")
public class Locacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOCACAO")
    private Integer id;

    @NotNull
    @Column(name = "DT_LOCACAO")
    private LocalDateTime dtLocacao;

    @NotNull
    @Column(name = "DT_DEVOLUCAO_PREVISTA")
    private LocalDateTime dtDevolucaoPrevista;

    @Column(name = "DT_DEVOLUCAO_EFETIVA")
    private LocalDateTime dtDevolucaoEfetiva;

    @NotNull
    @Column(name = "VALOR_COBRADO")
    private BigDecimal valorCobrado;

    @NotNull
    @Column(name = "MULTA_COBRADA")
    private BigDecimal multaCobrada;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ITEM", nullable = false)
    @NotNull
    private Item item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDtLocacao() {
        return dtLocacao;
    }

    public void setDtLocacao(LocalDateTime dtLocacao) {
        this.dtLocacao = dtLocacao;
    }

    public LocalDateTime getDtDevolucaoPrevista() {
        return dtDevolucaoPrevista;
    }

    public void setDtDevolucaoPrevista(LocalDateTime dtDevolucaoPrevista) {
        this.dtDevolucaoPrevista = dtDevolucaoPrevista;
    }

    public LocalDateTime getDtDevolucaoEfetiva() {
        return dtDevolucaoEfetiva;
    }

    public void setDtDevolucaoEfetiva(LocalDateTime dtDevolucaoEfetiva) {
        this.dtDevolucaoEfetiva = dtDevolucaoEfetiva;
    }

    public BigDecimal getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(BigDecimal valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public BigDecimal getMultaCobrada() {
        return multaCobrada;
    }

    public void setMultaCobrada(BigDecimal multaCobrada) {
        this.multaCobrada = multaCobrada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
