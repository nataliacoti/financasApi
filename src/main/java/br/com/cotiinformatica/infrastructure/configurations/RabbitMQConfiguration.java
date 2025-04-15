package br.com.cotiinformatica.infrastructure.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	/*
	 * Método para configurar a fila
	 * do servidor de mensageria
	 * do RabbitMQ
	 */
	@Bean
	public Queue queue() {
		return new Queue("apifinancas_contas");
	}
}
