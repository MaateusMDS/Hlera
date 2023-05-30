package com.hlera.model.familia;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "TB_HLERA_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UN_EMAIL", columnNames = "DS_EMAIL")
})

public class Usuario {
    @Id
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
