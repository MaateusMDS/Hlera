package com.hlera.model.familia;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_HLERA_FAMILIA")
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FAMILIA")
    @SequenceGenerator(
            name = "SQ_FAMILIA",
            sequenceName = "SQ_FAMILIA"
    )
    @Column(name = "ID_FAMILIA")
    private Long id;

    @Column(name = "NR_RENDA_SALARIAL")
    private double rendaSalarial;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_HLERA_MEMBROS",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_FAMILIA",
                            referencedColumnName = "ID_FAMILIA",
                            foreignKey = @ForeignKey(name = "FK_MEMBRO")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_PESSOA",
                            referencedColumnName = "ID_PESSOA",
                            foreignKey = @ForeignKey(name = "FK_PESSOA")
                    )
            }
    )

    private Set<Pessoa> membros = new LinkedHashSet<>();
}