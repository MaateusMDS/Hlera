package com.hlera.model.familia;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString

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
    @Getter @Setter
    private Long id;

    @Column(name = "NR_RENDA_SALARIAL")
    @Getter @Setter
    private double rendaSalarial;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_HLERA_MEMBROS",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_FAMILIA",
                            referencedColumnName = "ID_FAMILIA",
                            foreignKey = @ForeignKey(name = "FK_FAMILIA")
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

    public Familia addMembro(Pessoa membro) {
        this.membros.add(membro);
        return this;
    }

    public Familia removeMembro(Pessoa membro) {
        this.membros.remove(membro);
        return this;
    }

    public Set<Pessoa> getMembros() {
        return Collections.unmodifiableSet(membros);
    }
}