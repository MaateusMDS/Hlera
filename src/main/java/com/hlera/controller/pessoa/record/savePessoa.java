package com.hlera.controller.pessoa.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record savePessoa (
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        String rg,
        @NotNull @Past @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        @NotBlank
        String numeroCelular,
        @NotNull @Valid
        Dados dados,
        @NotNull @Valid
        DadosEndereco endereco
) {
}
