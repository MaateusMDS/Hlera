package com.hlera.controller.pessoa.record;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(
        @NotBlank
        String estado,
        @NotBlank
        String cidade,
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String cep,
        @NotBlank
        String numero,
        String complemento
) {
    
}
