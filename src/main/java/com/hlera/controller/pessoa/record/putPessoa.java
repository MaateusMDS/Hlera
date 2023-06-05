package com.hlera.controller.pessoa.record;

import java.time.LocalDate;

public record putPessoa(
        String nome,
        String cpf,
        String rg,
        LocalDate dataNascimento,
        String numeroCelular,
        String numeroTelefone,
        Dados dados,
        DadosEndereco endereco
) {
}
