package br.com.cotiinformatica.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.models.dtos.TipoRequestDto;
import br.com.cotiinformatica.domain.models.dtos.TipoResponseDto;
import br.com.cotiinformatica.domain.services.interfaces.TipoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tipo")
public class TipoController {

	@Autowired
	TipoService tipoService;

	@PostMapping("criar")
	public TipoResponseDto post(@RequestBody @Valid TipoRequestDto request) throws Exception {
		return tipoService.cadastrar(request);
	}

	@PutMapping("alterar/{id}")
	public TipoResponseDto put(@PathVariable Integer id, @RequestBody @Valid TipoRequestDto request) throws Exception {
		return tipoService.atualizar(id, request);
	}

	@DeleteMapping("excluir/{id}")
	public TipoResponseDto delete(@PathVariable Integer id) throws Exception {
		return tipoService.excluir(id);
	}

	@GetMapping("consultar")
	public List<TipoResponseDto> getAll() throws Exception {
		return tipoService.consultar();
	}

	@GetMapping("obter/{id}")
	public TipoResponseDto getById(@PathVariable Integer id) throws Exception {
		return tipoService.consultarPorId(id);
	}
}
