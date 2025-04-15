package br.com.cotiinformatica.domain.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoRequestDto {

    @Size(min = 6, max = 50, message = "Por favor, informe um nome de 6 a 50 caracteres.")
    @NotEmpty(message = "Por favor, informe o nome do tipo.")
    private String nome;
}
