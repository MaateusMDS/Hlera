package com.hlera.controller.campanha.record;

import com.hlera.model.Pessoa.Pessoa;
import com.hlera.model.campanha.Tipo;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record saveCampanha(
        @NotBlank
        String nome,
        @NotNull
        Tipo tipoCampanha,
        @NotBlank
        String descricao,
        @NotNull @Min(1)
        int itensDisponiveis
) {
}
