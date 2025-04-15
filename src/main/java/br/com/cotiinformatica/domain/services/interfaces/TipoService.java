package br.com.cotiinformatica.domain.services.interfaces;

import java.util.List;

import br.com.cotiinformatica.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.models.dtos.TipoResponseDto;

public interface TipoService {

	TipoResponseDto cadastrar(TipoRequestDto request) throws Exception;

	TipoResponseDto atualizar(Integer id, TipoRequestDto request) throws Exception;

	TipoResponseDto excluir(Integer id) throws Exception;

	List<TipoResponseDto> consultar() throws Exception;

	TipoResponseDto consultarPorId(Integer id) throws Exception;
}
