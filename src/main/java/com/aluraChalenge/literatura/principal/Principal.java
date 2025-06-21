package com.aluraChalenge.literatura.principal;
import com.aluraChalenge.literatura.controller.OpcionesMenu;
import com.aluraChalenge.literatura.utilidades.Textos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
public class Principal {


    @Autowired
    private OpcionesMenu menu;

  public void mostrarMenu(){
      Scanner scanner = new Scanner(System.in);
      int opcion = 0;
      System.out.println(Textos.SALUDO);
      while (opcion !=7 ) {
          System.out.println(" ");
          System.out.println(Textos.MENU);
          System.out.println(Textos.TITULO_MENU);
          System.out.println(" ");
          try {
              opcion = Integer.parseInt(scanner.nextLine());

              switch (opcion){
                  case 1:
                    menu.busquedaPorTitulo();
                  break;
                  case 2:
                      menu.mostrarLibrosRegistrados();
                      break;
                  case 3:
                      menu.mostrarAutoresEnBase();
                      break;
                  case 4:
                      menu.buscarAutoresVivosPorAnio();
                      break;
                  case 5:
                      menu.mostrarLibrosPorIdioma();
                      break;
                  case 6:menu.mostrarLibrosMasDescargados();
                      break;
                  case 7:
                      System.out.println(Textos.FINAL);
                      break;
                  default:
                      System.out.println(Textos.ERROR);

              }
          } catch (NumberFormatException e) {
              System.out.println(Textos.ERROR2);
              System.out.println(" ");
              System.out.println(" ");
          }
      }
  }
}
