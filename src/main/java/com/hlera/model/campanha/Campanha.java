package com.hlera.model.campanha;

import com.hlera.controller.campanha.record.putCampanha;
import com.hlera.controller.campanha.record.saveCampanha;
import com.hlera.model.Pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
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
    @Getter
    private Long id;
    @Column(name = "NM_CAMPANHA")
    @Getter @Setter
    private String nome;
    @Column(name = "TP_CAMPANHA")
    @Getter @Setter
    private Tipo tipoCampanha;
    @Column(name = "NR_ITENS_DISPONIVEIS")
    @Getter @Setter
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

    public Campanha(saveCampanha dados) {
        this.nome = dados.nome();
        this.tipoCampanha = dados.tipoCampanha();
        this.itensDisponiveis = dados.itensDisponiveis();
    }
    
    public void atualizar(putCampanha dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.tipoCampanha() != null){
            this.tipoCampanha = dados.tipoCampanha();
        }
        if (dados.itensDisponiveis() != 0){
            this.itensDisponiveis = dados.itensDisponiveis();
        }
        if (dados.inscritos() != null){
            this.inscritos = dados.inscritos();
        }
    }
}
