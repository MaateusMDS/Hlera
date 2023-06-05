package com.hlera.repository;

import com.hlera.controller.pessoa.record.Dados;
import com.hlera.model.Pessoa.Pessoa;;
import com.hlera.model.Pessoa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT u FROM Pessoa u WHERE u.dados.email = :user OR u.cpf = :user OR u.rg = :user OR u.numeroCelular = :user")
    Optional<Pessoa> findUser(@Param("user") String user);
}
