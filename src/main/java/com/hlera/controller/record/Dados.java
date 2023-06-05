package com.hlera.controller.record;

import com.hlera.model.Pessoa.Grupo;

public record Dados (
        String email,
        String senha,
        Grupo grupo
) {
}
