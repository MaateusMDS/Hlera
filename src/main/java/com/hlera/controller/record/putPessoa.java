package com.hlera.controller.record;

import com.hlera.model.endereco.Endereco;
import com.hlera.model.Pessoa.Usuario;

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
