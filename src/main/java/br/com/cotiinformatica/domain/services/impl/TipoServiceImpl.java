package br.com.cotiinformatica.domain.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.models.dtos.TipoResponseDto;
import br.com.cotiinformatica.domain.models.entities.Tipo;
import br.com.cotiinformatica.domain.services.interfaces.TipoService;
import br.com.cotiinformatica.infrastructure.repositories.TipoRepository;

@Service
public class TipoServiceImpl implements TipoService {
	
	@Autowired TipoRepository tipoRepository;

	@Override
	public TipoResponseDto cadastrar(TipoRequestDto request) throws Exception {
		
		var tipo = new Tipo(); //instanciando um objeto da classe Tipo
		tipo.setNome(request.getNome()); //capturando o campo nome
		
		tipoRepository.save(tipo); //gravando o tipo no banco de dados
		
		//retornando os dados do tipo cadastrado
		return toResponse(tipo);
	}

	@Override
	public TipoResponseDto atualizar(Integer id, TipoRequestDto request) throws Exception {

		//verificar se o tipo ja existe no banco de dados
		var registro = tipoRepository.findById(id);
		
		//se o tipo nao existir
		if(registro.isEmpty()) {
			throw new IllegalArgumentException("Tipo não encontrado, verifique o ID informado.");
		}
		
		//capturando os dados do tipo existente
		var tipo = registro.get();
		
		//alterar os dados do tipo
		tipo.setNome(request.getNome());
		
		//gravando as alteracoes no banco de dados
		tipoRepository.save(tipo);
		
		//retornando os dados do tipo modificado
		return toResponse(tipo);
	}

	@Override
	public TipoResponseDto excluir(Integer id) throws Exception {

		//verificar se o tipo ja existe no banco de dados
		var registro = tipoRepository.findById(id);
		
		//se o tipo nao existir
		if(registro.isEmpty()) {
			throw new IllegalArgumentException("Tipo não encontrado, verifique o ID informado.");
		}
		
		//capturando os dados do tipo existente
		var tipo = registro.get();
				
		//excluindo o tipo do banco de dados
		tipoRepository.delete(tipo);
		
		//retornando os dados do tipo excluído
		return toResponse(tipo);
	}

	@Override
	public List<TipoResponseDto> consultar() throws Exception {

		//recuperando os tipos cadastrados no banco de dados
		var tipos = tipoRepository.findAll();
		
		//lista para armazenar os tipos a serem retornados
		var result = new ArrayList<TipoResponseDto>();
		for(var tipo : tipos) {
			result.add(toResponse(tipo));
		}
		
		return result; //retornando os tipos encontrados
	}

	@Override
	public TipoResponseDto consultarPorId(Integer id) throws Exception {

		//verificar se o tipo ja existe no banco de dados
		var registro = tipoRepository.findById(id);
		
		//se o tipo nao existir
		if(registro.isEmpty()) {
			throw new IllegalArgumentException("Tipo não encontrado, verifique o ID informado.");
		}
		
		//capturando os dados do tipo existente
		var tipo = registro.get();
		
		//retornando os dados do tipo encontrado
		return toResponse(tipo);
	}
	
	private TipoResponseDto toResponse(Tipo tipo) {
		//criando o objeto response para devolver os dados do tipo cadastrado
		var response = new TipoResponseDto();
		response.setId(tipo.getId());
		response.setNome(tipo.getNome());
		
		return response;
	}

}










