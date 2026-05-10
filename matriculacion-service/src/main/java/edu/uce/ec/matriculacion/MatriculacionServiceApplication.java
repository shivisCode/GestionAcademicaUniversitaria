package edu.uce.ec.matriculacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MatriculacionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculacionServiceApplication.class, args);
	}

}
