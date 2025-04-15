package br.com.cotiinformatica.domain.services.interfaces;

import java.util.List;

import br.com.cotiinformatica.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.ContaResponseDto;

public interface ContaService {

	ContaResponseDto cadastrar(ContaRequestDto request) throws Exception;

	ContaResponseDto atualizar(Integer id, ContaRequestDto request) throws Exception;

	ContaResponseDto excluir(Integer id) throws Exception;

	List<ContaResponseDto> consultar() throws Exception;

	ContaResponseDto consultarPorId(Integer id) throws Exception;
}
