package com.aluraChalenge.literatura.repository;


import com.aluraChalenge.literatura.model.AutorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorEntidad, Long> {

        Optional<AutorEntidad> findByNombreIgnoreCase(String nombre);

        @Query("SELECT a FROM AutorEntidad a LEFT JOIN FETCH a.libros ORDER BY a.nombre")
    List<AutorEntidad> TodosLosAutoresConLibros();

    @Query("SELECT a FROM AutorEntidad a WHERE a.nacimiento <= :anio AND (a.fallecimiento IS NULL OR a.fallecimiento > :anio)")
    List<AutorEntidad> buscarAutoresVivosEn(int anio);



}


