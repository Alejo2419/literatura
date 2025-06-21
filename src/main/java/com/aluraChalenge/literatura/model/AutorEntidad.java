package com.aluraChalenge.literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class AutorEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<LibroEntidad> libros = new ArrayList<>();

    public AutorEntidad() {}

    public AutorEntidad(String nombre, Integer nacimiento, Integer fallecimiento) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.fallecimiento = fallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<LibroEntidad> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroEntidad> libros) {
        this.libros = libros;
    }
}
