package com.aluraChalenge.literatura.controller;


import com.aluraChalenge.literatura.model.AutorEntidad;
import com.aluraChalenge.literatura.model.Datos;
import com.aluraChalenge.literatura.model.DatosLibros;
import com.aluraChalenge.literatura.model.LibroEntidad;
import com.aluraChalenge.literatura.repository.AutorRepository;
import com.aluraChalenge.literatura.repository.Repositorio;
import com.aluraChalenge.literatura.service.ConsumoApi;
import com.aluraChalenge.literatura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aluraChalenge.literatura.utilidades.Textos;

import java.util.*;



@Component
public class OpcionesMenu {

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private ConsumoApi consumoApi;
    @Autowired
    private ConvierteDatos conversor;
    @Autowired
    private Repositorio libroRepository;
    @Autowired
    private AutorRepository autorRepository;




    public void busquedaPorTitulo() {
        System.out.println("🔎 Ingrese el título del libro: ");
        String titulo = scanner.nextLine().trim();

        if (titulo.isBlank() || titulo.matches(".*[^\\w\\sáéíóúÁÉÍÓÚñÑ+\\-*/.,!?¡¿].*")) {
            System.out.println(Textos.ERROR3);
            return;
        }

        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "+");
        String json = consumoApi.obtenerDatos(url);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        List<DatosLibros> resultados = datos.resultados();

        if (resultados.isEmpty()) {
            System.out.println("❌ No se encontró ningún libro con este título.");
            return;
        }

        int limite = Math.min(resultados.size(), 3);
        int guardados = 0;
        int existentes = 0;

        for (int i = 0; i < limite; i++) {
            DatosLibros libro = resultados.get(i);

            Optional<LibroEntidad> libroExistente = libroRepository.findByTituloIgnoreCase(libro.titulo());
            if (libroExistente.isPresent()) {
                LibroEntidad libroGuardado = libroExistente.get();

                Textos.mostrarLibro(libroGuardado);
                System.out.println(Textos.SEPARADOR);
                System.out.println("📌 Este libro ya estaba registrado en la base de datos.\n");
                existentes++;
                continue;
            }

            String nombreAutor = libro.autor().isEmpty() ? "Desconocido" : libro.autor().get(0).nombreAutor();
            Integer nacimiento = libro.autor().isEmpty() ? null : libro.autor().get(0).fechaDeNacimiento();
            Integer fallecimiento = libro.autor().isEmpty() ? null : libro.autor().get(0).FechaDeMuerte();

            AutorEntidad autor = autorRepository.findByNombreIgnoreCase(nombreAutor)
                    .orElseGet(() -> {
                        AutorEntidad nuevoAutor = new AutorEntidad(nombreAutor, nacimiento, fallecimiento);
                        return autorRepository.save(nuevoAutor);
                    });

            LibroEntidad entidad = new LibroEntidad(
                    libro.titulo(),
                    String.join(",", libro.idiomas()),
                    libro.descargasTotales(),
                    libro.formatos().getOrDefault("text/html", "No disponible"),
                    autor
            );

            libroRepository.save(entidad);
            guardados++;

            System.out.println(Textos.SEPARADOR2);
            System.out.println("📖 Título: " + libro.titulo());
            System.out.println("👤 Autor: " + nombreAutor);
            System.out.println("🌐 Idiomas: " + libro.idiomas());
            System.out.println("⬇ Descargas: " + libro.descargasTotales());
            System.out.println("🔗 Enlace: " + libro.formatos().getOrDefault("text/html", "No disponible"));
            System.out.println(Textos.SEPARADOR2);
            System.out.println("✅ Libro guardado exitosamente.\n");
        }

        System.out.println("📚 Resumen de búsqueda:");
        System.out.println("✅ Nuevos libros guardados: " + guardados);
        System.out.println("📌 Ya registrados previamente: " + existentes);
    }




    public void mostrarLibrosRegistrados() {
        List<LibroEntidad> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("❌ No hay libros registrados en la base de datos.");
            return;
        }

        System.out.println("\n📚 Libros registrados en la base de datos:");
        System.out.println(Textos.SEPARADOR);

        for (LibroEntidad libro : libros) {
            Textos.mostrarLibro(libro);
        }
    }

    public void mostrarAutoresEnBase() {
        List<AutorEntidad> autores = autorRepository.TodosLosAutoresConLibros();
        if (autores.isEmpty()){
            System.out.println("no hay autores en la base de datos");

        }
        System.out.println(" Autores en la base de datos con sus libros");
        System.out.println("Los resultados se muestran alfabeticamente: ");

        for (AutorEntidad autor : autores){
            System.out.println(Textos.SEPARADOR2);
            System.out.println(Textos.SEPARADOR);
            System.out.println("\n👤 Autor: " + autor.getNombre() + " (" + autor.getLibros().size() + " libro(s))");

            for (LibroEntidad  libro : autor.getLibros()) {
                System.out.println("   📘 " + libro.getTitulo());

            }
        }

    }

    public void buscarAutoresVivosPorAnio() {
        System.out.println("📅 Ingresa un año para buscar autores vivos:");
        try {
            int anio = Integer.parseInt(scanner.nextLine());

            List<AutorEntidad> autoresVivos = autorRepository.buscarAutoresVivosEn(anio);

            if (autoresVivos.isEmpty()) {
                System.out.println("❌ No se encontraron autores vivos para el año " + anio);
                System.out.println(Textos.SEPARADOR2);
                return;
            }

            System.out.println("\n📚 Autores vivos para el año " + anio + ":");
            for (AutorEntidad autor : autoresVivos) {
                System.out.printf("👤 %s (%s - %s)%n",
                        autor.getNombre(),
                        autor.getNacimiento() != null ? autor.getNacimiento() : "¿?",
                        autor.getFallecimiento() != null ? autor.getFallecimiento() : "¿?");
            }

        } catch (NumberFormatException e) {
            System.out.println(Textos.ERROR2);
        }
    }

    public void mostrarLibrosPorIdioma() {
        System.out.print("🌐 Ingresa el código de idioma (ej: en, es, fr): ");
        String idioma = scanner.nextLine().trim().toLowerCase();

        List<LibroEntidad> libros = libroRepository.findAll();

        List<LibroEntidad> librosFiltrados = libros.stream()
                .filter(libro -> libro.getIdiomas().toLowerCase().contains(idioma))
                .toList();

        if (librosFiltrados.isEmpty()) {
            System.out.println("❌ No hay libros registrados en el idioma '" + idioma + "'.");
            return;
        }

        System.out.println("\n📚 Libros en idioma '" + idioma + "':");
        System.out.println(Textos.SEPARADOR);
        System.out.println(Textos.SEPARADOR);

        for (LibroEntidad libro : librosFiltrados) {
           Textos.mostrarLibro(libro);
        }
    }

    public void mostrarLibrosMasDescargados() {
        List<LibroEntidad> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("❌ No hay libros registrados en la base de datos.");
            return;
        }

        System.out.println("\n📚 5 Libros más descargados:");
        System.out.println(Textos.SEPARADOR);
        System.out.println(Textos.SEPARADOR);

        libros.stream()
                .sorted(Comparator.comparing(LibroEntidad::getDescargas).reversed())
                .limit(5)
                .forEach(Textos::mostrarLibro);
    }
}
