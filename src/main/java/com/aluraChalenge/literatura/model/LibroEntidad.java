package com.aluraChalenge.literatura.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "libros")
public class LibroEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idiomas;
    private Double descargas;
    private String enlace;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorEntidad autor;


    public LibroEntidad(){}

    public LibroEntidad(String titulo, String idiomas, Double descargas, String enlace, AutorEntidad autor) {
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.descargas = descargas;
        this.enlace = enlace;
        this.autor = autor;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public AutorEntidad getAutor() {
        return autor;
    }

    public void setAutor(AutorEntidad autor) {
        this.autor = autor;
    }
}
