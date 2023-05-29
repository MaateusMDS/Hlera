package com.hlera.model.familia;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @SequenceGenerator(
            name = "SQ_FAMILIA",
            sequenceName = "SQ_FAMILIA"
    )
    private Long id;
    private double rendaSalarial;
//    private Set<Pessoa> membros = new LinkedHashSet<>();

}
