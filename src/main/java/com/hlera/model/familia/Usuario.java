package com.hlera.model.familia;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table(name = "TB_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UN_EMAIL", columnNames = "DS_EMAIL")
})
public class Usuario {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "ID_PESSOA", referencedColumnName = "ID_PESSOA"
    )
    private Pessoa id;
    @Getter @Setter
    @Column(name = "DS_EMAIL")
    private String email;
    @Column(name = "DS_SENHA")
    private String senha;

}
