package com.aluraChalenge.literatura.utilidades;

import com.aluraChalenge.literatura.model.LibroEntidad;

public class Textos {
   public static final  String MENU = "1- Buscar libro por titulo\n" +
            "2- Mostrar libros registrados\n" +
            "3- Mostrar autores ya buscados o guardados anteriormente\n" +
            "4- Buscar autores vivos en un año especifico\n" +
            "5- Buscar libros por idioma\n" +
            "6- Mostrar los 5 libros mas descargados\n"+
            "7- Salir";

   public static final  String TITULO_MENU = "--- elige una opcion --- ";
   public static final  String SALUDO = "📚 Bienvenido al menú principal";
   public static final  String ERROR = "❌ Ha ocurrido un error";
   public static final  String FINAL = "👋 ¡Gracias por usar mi APP!";
   public static final  String ERROR2 = "❌ Entrada inválida. Ingresa un número válido.";
   public static final  String SEPARADOR = "--------------------------------------------------";
   public static final  String ERROR3 = "\"❌ Entrada inválida. Solo se permiten letras, números, espacios y estos signos: + - * / . , ! ? ¡ ¿\"";


   public static void  mostrarLibro(LibroEntidad libro) {
      System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
      System.out.println("📖 Título: " + libro.getTitulo());
      System.out.println("✍ Autor: " + libro.getAutor().getNombre());
      System.out.println("🌐 Idiomas: " + libro.getIdiomas());
      System.out.println("⬇ Descargas: " + libro.getDescargas());
      System.out.println("🔗 Enlace: " + libro.getEnlace());
      System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
   }


}
