package com.aluraChalenge.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
      @JsonAlias("title") String titulo,
      @JsonAlias("authors")  List<DatosAutor> autor,
      @JsonAlias("languages")  List<String> idiomas,
      @JsonAlias("download_count")  double descargasTotales,
      @JsonAlias("formats")Map<String, String> formatos

) {
    public String descarga(){
        return formatos.getOrDefault("text/html", "Descarga no disponible");
    }
}
