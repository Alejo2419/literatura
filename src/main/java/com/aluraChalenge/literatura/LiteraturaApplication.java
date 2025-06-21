package com.aluraChalenge.literatura;

import com.aluraChalenge.literatura.principal.Principal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LiteraturaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LiteraturaApplication.class, args);

		Principal principal = context.getBean(Principal.class);

		principal.mostrarMenu();
	}
}
