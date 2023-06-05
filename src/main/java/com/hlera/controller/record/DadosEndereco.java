package com.hlera.controller.record;

public record DadosEndereco(
         String estado,
         String cidade,
         String logradouro,
         String bairro,
         String cep,
         String numero,
         String complemento
) {
    
}
