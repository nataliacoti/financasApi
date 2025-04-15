package br.com.cotiinformatica.domain.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.ContaResponseDto;
import br.com.cotiinformatica.domain.models.entities.Conta;
import br.com.cotiinformatica.domain.models.enums.Movimentacao;
import br.com.cotiinformatica.domain.services.interfaces.ContaService;
import br.com.cotiinformatica.infrastructure.components.LogFinancasComponent;
import br.com.cotiinformatica.infrastructure.components.LogFinancasComponent.Operacao;
import br.com.cotiinformatica.infrastructure.components.RabbitMQProducerComponent;
import br.com.cotiinformatica.infrastructure.repositories.ContaRepository;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired ContaRepository contaRepository;
	@Autowired RabbitMQProducerComponent producerComponent;
	@Autowired LogFinancasComponent logFinancasComponent;
	
	@Override
	public ContaResponseDto cadastrar(ContaRequestDto request) throws Exception {

		var conta = new Conta(); //instanciando um objeto da classe Conta
		
		conta.setNome(request.getNome()); //capturando o campo nome;
		conta.setData(new SimpleDateFormat("dd/MM/yyyy").parse(request.getData())); //capturando o campo data;
		conta.setValor(BigDecimal.valueOf(request.getValor())); //capturando o campo valor;
		conta.setMovimentacao(request.getMovimentacao().equals("1") //Se movimentação é igual a 1
									? Movimentacao.RECEITA :  //então movimentação é Receita
										request.getMovimentacao().equals("2") //Se movimentação é igual 2
											? Movimentacao.DESPESA //então movimentação é Despesa 
													: null); //senão é igual a vazio
		
		//gravando a conta no banco de dados
		contaRepository.save(conta);
		
		//enviando os dados da conta para o RabbitMQ
		producerComponent.sendMessage(conta);
		
		//registrando no log
		logFinancasComponent.gerarLog(conta, Operacao.CADASTRO);
		
		return toResponse(conta);
	}

	@Override
	public ContaResponseDto atualizar(Integer id, ContaRequestDto request) throws Exception {

		var registro = contaRepository.findById(id);
		
		if(registro.isEmpty())
			throw new IllegalArgumentException("Conta não encontrada, verifique o ID informado.");
		
		var conta = registro.get();
		
		conta.setNome(request.getNome()); 
		conta.setData(new SimpleDateFormat("dd/MM/yyyy").parse(request.getData()));
		conta.setValor(BigDecimal.valueOf(request.getValor()));
		conta.setMovimentacao(request.getMovimentacao() == "1"
									? Movimentacao.RECEITA : 
										request.getMovimentacao() == "2"
											? Movimentacao.DESPESA 
													: null);
		
		contaRepository.save(conta);
		
		logFinancasComponent.gerarLog(conta, Operacao.ALTERACAO);
		
		return toResponse(conta);
	}

	@Override
	public ContaResponseDto excluir(Integer id) throws Exception {

		var registro = contaRepository.findById(id);
		
		if(registro.isEmpty())
			throw new IllegalArgumentException("Conta não encontrada, verifique o ID informado.");
		
		var conta = registro.get();
		
		contaRepository.delete(conta);
		
		logFinancasComponent.gerarLog(conta, Operacao.EXCLUSAO);
		
		return toResponse(conta);
	}

	@Override
	public List<ContaResponseDto> consultar() throws Exception {

		var contas = contaRepository.findAll();
		var result = new ArrayList<ContaResponseDto>();
		
		for (var conta : contas) {
			result.add(toResponse(conta));
		}
		
		return result;
	}

	@Override
	public ContaResponseDto consultarPorId(Integer id) throws Exception {
		
		var registro = contaRepository.findById(id);
		
		if(registro.isEmpty())
			throw new IllegalArgumentException("Conta não encontrada, verifique o ID informado.");
		
		var conta = registro.get();
		
		return toResponse(conta);
	}
	
	private ContaResponseDto toResponse(Conta conta) {
		
		var response = new ContaResponseDto();
		
		response.setId(conta.getId());
		response.setNome(conta.getNome());
		response.setData(new SimpleDateFormat("dd/MM/yyyy").format(conta.getData()));
		response.setValor(conta.getValor().doubleValue());
		response.setMovimentacao(conta.getMovimentacao().toString());
		
		return response;
	}

}







