package com.hlera.model.familia;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter

@Embeddable
public class Usuario {
    @Column(name = "DS_EMAIL")
    private String email;
    @Column(name = "DS_SENHA")
    private String senha;
    @Column(name = "GR_PESSOA")
    private Grupo grupo;
}
