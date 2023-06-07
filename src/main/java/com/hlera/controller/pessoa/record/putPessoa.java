package com.hlera.controller.pessoa.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record putPessoa(
        String nome,
        String cpf,
        String rg,
        @Past @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        String numeroCelular,
        Dados dados,
        DadosEndereco endereco
) {
}
