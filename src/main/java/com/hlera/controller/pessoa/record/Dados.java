package com.hlera.controller.pessoa.record;

import com.hlera.model.Pessoa.Grupo;

public record Dados (
        String email,
        String senha,
        Grupo grupo
) {
}
