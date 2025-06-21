package com.aluraChalenge.literatura.repository;

import com.aluraChalenge.literatura.model.AutorEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aluraChalenge.literatura.model.LibroEntidad;

import java.util.Optional;

public interface Repositorio extends JpaRepository<LibroEntidad, Long> {

    Optional<LibroEntidad> findByTituloIgnoreCase(String titulo);

}
