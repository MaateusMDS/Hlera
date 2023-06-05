package com.hlera.controller.campanha.record;

import com.hlera.model.Pessoa.Pessoa;
import com.hlera.model.campanha.Tipo;
import jakarta.persistence.Column;

import java.util.Set;

public record saveCampanha(
         String nome,
         Tipo tipoCampanha,
         int itensDisponiveis
) {
}