package com.hlera.model.familia;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "TB_HLERA_PESSOA")
public class Pessoa {
    @Id
    @GeneratedValue(generator = "SQ_PESSOA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PESSOA")
    private Long id;
    @Column(name = "NM_PESSOA")
    private String nome;
    @Column(name = "CPF_PESSOA")
    private String cpf;
    @Column(name = "RG_PESSOA")
    private String rg;
    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;
}
