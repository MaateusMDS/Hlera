package com.hlera.model.campanha;

import com.hlera.model.Pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "TB_HLERA_CAMPANHA")

public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CAMPANHA")
    @SequenceGenerator(
            name = "SQ_CAMPANHA",
            sequenceName = "SQ_CAMPANHA"
    )
    @Column(name = "ID_CAMPANHA")
    private Long id;
    @Column(name = "NM_CAMPANHA")
    private String nome;
    @Column(name = "TP_CAMPANHA")
    private Tipo tipoCampanha;
    @Column(name = "NR_ITENS_DISPONIVEIS")
    private int itensDisponiveis;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_HLERA_INSCRICOES",
            joinColumns = {
                    @JoinColumn(
                            name = "ID_CAMPANHA",
                            referencedColumnName = "ID_CAMPANHA",
                            foreignKey = @ForeignKey(name = "FK_CAMPANHA")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ID_PESSOA",
                            referencedColumnName = "ID_PESSOA",
                            foreignKey = @ForeignKey(name = "FK_PESSOA_CAMPANHA")
                    )
            }
    )
    private Set<Pessoa> inscritos = new LinkedHashSet<>();

    public Campanha addInscrito(Pessoa inscrito) {
        this.inscritos.add(inscrito);
        return this;
    }

    public Campanha removeInscrito(Pessoa inscrito) {
        this.inscritos.remove(inscrito);
        return this;
    }

    public Set<Pessoa> getInscritos() {
        return Collections.unmodifiableSet(inscritos);
    }
}
