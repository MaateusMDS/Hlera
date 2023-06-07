package com.hlera.model.Pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hlera.controller.pessoa.record.Dados;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter

@Embeddable
public class Usuario {
    @Column(name = "DS_EMAIL")
    private String email;
    @Column(name = "DS_SENHA") @JsonIgnore
    private String senha;
    @Column(name = "GR_PESSOA")
    private Grupo grupo;

    public Usuario(Dados dados) {
        this.email = dados.email();
        this.senha = dados.senha();
        this.grupo = dados.grupo();
    }

    public void atualizar(Dados dados) {
        if(dados.email() != null){
            this.email = dados.email();
        }
        if(dados.senha() != null){
            this.senha = dados.senha();
        }
        if(dados.grupo() != null){
            this.grupo = dados.grupo();
        }
    }
}
