package br.com.cotiinformatica.infrastructure.components;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.domain.collections.LogFinancas;
import br.com.cotiinformatica.domain.models.entities.Conta;
import br.com.cotiinformatica.infrastructure.repositories.LogFinancasRepository;

@Component
public class LogFinancasComponent {

	//injeção de dependência
	@Autowired LogFinancasRepository logFinancasRepository;
	
	/*
	 * Método para gravar um log de conta no banco de dados
	 */
	public void gerarLog(Conta conta, Operacao operacao) {
		
		var logFinancas = new LogFinancas();
		
		//preenchendo os dados para gravação do log
		logFinancas.setId(UUID.randomUUID()); //gerando um id único		
		logFinancas.setDataHora(Instant.now()); //gerando data e hora atual
		
		logFinancas.setIdConta(conta.getId());
		logFinancas.setNomeConta(conta.getNome());
		logFinancas.setDataConta(conta.getData());
		logFinancas.setValorConta(conta.getValor().doubleValue());
		logFinancas.setMovimentacao(conta.getMovimentacao().name());
		
		switch(operacao) {
		case CADASTRO:
			logFinancas.setDescricao("Cadastro de conta");
			break;
		case ALTERACAO:
			logFinancas.setDescricao("Atualização de conta");
			break;
		case EXCLUSAO:
			logFinancas.setDescricao("Exclusão de conta");
			break;
		}
		
		//gravando o log no banco de dados
		logFinancasRepository.save(logFinancas);
	}
	
	/*
	 * Enum para definirmos o tipo de operação do log
	 */
	public enum Operacao {
		CADASTRO,
		ALTERACAO,
		EXCLUSAO
	}
}
