package com.hlera.controller.pessoa.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String estado,
        @NotBlank
        String cidade,
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank @Pattern(regexp = "(^[0-9]{5})-?([0-9]{3}$)")
        String cep,
        @NotBlank @Pattern(regexp = "^\\d+$")
        String numero,
        String complemento
) {
    
}
