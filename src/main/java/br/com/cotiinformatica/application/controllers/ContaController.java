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

import br.com.cotiinformatica.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.ContaResponseDto;
import br.com.cotiinformatica.domain.services.interfaces.ContaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/conta")
public class ContaController {

	@Autowired
	ContaService contaService;

	@PostMapping("criar")
	public ContaResponseDto post(@RequestBody @Valid ContaRequestDto request) throws Exception {
		return contaService.cadastrar(request);
	}

	@PutMapping("alterar/{id}")
	public ContaResponseDto put(@PathVariable Integer id, @RequestBody @Valid ContaRequestDto request)
			throws Exception {
		return contaService.atualizar(id, request);
	}

	@DeleteMapping("excluir/{id}")
	public ContaResponseDto delete(@PathVariable Integer id) throws Exception {
		return contaService.excluir(id);
	}

	@GetMapping("consultar")
	public List<ContaResponseDto> getAll() throws Exception {
		return contaService.consultar();
	}

	@GetMapping("obter/{id}")
	public ContaResponseDto getById(@PathVariable Integer id) throws Exception {
		return contaService.consultarPorId(id);
	}
}
