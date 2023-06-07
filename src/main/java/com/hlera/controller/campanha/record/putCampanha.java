package com.hlera.controller.campanha.record;

import com.hlera.model.Pessoa.Pessoa;
import com.hlera.model.campanha.Tipo;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.util.Set;

public record putCampanha(
         String nome,
         Tipo tipoCampanha,
         @Min(1)
         int itensDisponiveis,
         String descricao,
         Set<Pessoa> inscritos
) {
}
