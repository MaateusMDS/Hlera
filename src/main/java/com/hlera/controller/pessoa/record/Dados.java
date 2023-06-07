package com.hlera.controller.pessoa.record;

import com.hlera.model.Pessoa.Grupo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Dados (
        @NotBlank @Email
        String email,
        @NotBlank
        String senha,
        @NotNull
        Grupo grupo
) {
}
