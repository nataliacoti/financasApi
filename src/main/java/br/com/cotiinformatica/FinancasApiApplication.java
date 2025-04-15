package br.com.cotiinformatica;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class FinancasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancasApiApplication.class, args);
	}

}
