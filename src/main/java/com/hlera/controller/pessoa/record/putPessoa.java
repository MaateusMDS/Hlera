package com.hlera.controller.pessoa.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record putPessoa(
        String nome,
        @Pattern(regexp = "^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}$")
        String cpf,
        @Pattern(regexp = "/(^\\d{1,2}).?(\\d{3}).?(\\d{3})-?(\\d{1}|X|x$)/")
        String rg,
        @Past @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        @Pattern(regexp = "/^\\d{11}$/")
        String numeroCelular,
        Dados dados,
        DadosEndereco endereco
) {
}
