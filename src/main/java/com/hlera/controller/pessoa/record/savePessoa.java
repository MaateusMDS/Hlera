package com.hlera.controller.pessoa.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record savePessoa (
        @NotBlank
        String nome,
        @NotBlank @Pattern(regexp = "^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}$")
        String cpf,
        @NotBlank @Pattern(regexp = "(^\\d{1,2}).?(\\d{3}).?(\\d{3})-?(\\d{1}|X|x$)")
        String rg,
        @NotNull @Past @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        @NotBlank @Pattern(regexp = "(^[0-9]{2})?(\\s|-)?(9?[0-9]{4})-?([0-9]{4}$)")
        String numeroCelular,
        @NotNull @Valid
        Dados dados,
        @NotNull @Valid
        DadosEndereco endereco
) {
}
