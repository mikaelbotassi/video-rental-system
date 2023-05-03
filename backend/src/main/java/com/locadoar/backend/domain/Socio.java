package com.locadoar.backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "SOCIO")
@PrimaryKeyJoinColumn(name = "ID_CLIENTE")
public class Socio extends Cliente{

    @NotNull
    @Column(name = "CPF", nullable = false, length = 11)
    private String cpf;

    @Column(name = "ENDERECO", length = 1000)
    private String endereco;

    @NotNull
    @Column(name = "TEL", length = 11)
    private String telefone;

    @OneToMany(mappedBy = "socio", orphanRemoval = true, cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Set<Dependente> dependentes;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(Set<Dependente> dependentes) {
        this.dependentes = dependentes;
    }
}
