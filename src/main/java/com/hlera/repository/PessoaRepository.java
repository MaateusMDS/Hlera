package com.hlera.repository;

import com.hlera.model.familia.Pessoa;;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
