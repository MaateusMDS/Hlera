package com.hlera.controller.campanha.record;

import com.hlera.model.Pessoa.Pessoa;
import com.hlera.model.campanha.Tipo;

import java.util.Set;

public record putCampanha(
         String nome,
         Tipo tipoCampanha,
         int itensDisponiveis,
         Set<Pessoa> inscritos
) {
}
