package com.hlera.repository;

import com.hlera.model.Pessoa.Pessoa;
import com.hlera.model.campanha.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
    @Query("SELECT c FROM Campanha c JOIN c.inscritos p WHERE p.id = :id")
    Set<Campanha> findCampanhaByUser(@Param("id") long id);
}
