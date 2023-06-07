package com.hlera.model.Pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hlera.controller.pessoa.record.putPessoa;
import com.hlera.controller.pessoa.record.savePessoa;
import com.hlera.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "TB_HLERA_PESSOA", uniqueConstraints = {
        @UniqueConstraint(name = "UN_CPF", columnNames = "CPF_PESSOA"),
        @UniqueConstraint(name = "UN_RG", columnNames = "RG_PESSOA"),
        @UniqueConstraint(name = "UN_CELULAR", columnNames = "NR_CELULAR"),
        @UniqueConstraint(name = "UN_EMAIL", columnNames = "DS_EMAIL")
})
public class Pessoa {
    @Id
    @GeneratedValue(generator = "SQ_PESSOA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PESSOA")
    @Column(name = "ID_PESSOA")
    private Long id;
    @Column(name = "NM_PESSOA")
    private String nome;
    @Column(name = "CPF_PESSOA")
    private String cpf;
    @Column(name = "RG_PESSOA")
    private String rg;
    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;
    @Column(name = "NR_CELULAR")
    private String numeroCelular;
    @Embedded
    private Usuario dados;
    @Embedded
    private Endereco endereco;

    public Pessoa(savePessoa dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.rg = dados.rg();
        this.dataNascimento = dados.dataNascimento();
        this.numeroCelular = dados.numeroCelular();
        this.dados = new Usuario(dados.dados());
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarPessoa(putPessoa dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.cpf() != null){
            this.cpf = dados.cpf();
        }
        if(dados.rg() != null){
            this.rg = dados.rg();
        }
        if(dados.dataNascimento() != null){
            this.dataNascimento = dados.dataNascimento();
        }
        if(dados.numeroCelular() != null){
            this.numeroCelular = dados.numeroCelular();
        }
        if(dados.dados() != null){
            this.dados.atualizar(dados.dados());
        }
        if(dados.endereco() != null){
            this.endereco.atualizar(dados.endereco());
        }
    }
}
