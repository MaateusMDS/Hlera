package com.hlera.controller.record;

import com.hlera.model.endereco.Endereco;
import com.hlera.model.familia.Usuario;

import java.time.LocalDate;

public record putPessoa(
        String nome,
        String cpf,
        String rg,
        LocalDate dataNascimento,
        String numeroCelular,
        String numeroTelefone,
        Usuario dados,
        Endereco endereco
) {
}
