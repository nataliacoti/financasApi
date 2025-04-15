package br.com.cotiinformatica.domain.models.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContaRequestDto {

    @Size(min = 6, max = 100, message = "Por favor, informe um nome de 6 a 100 caracteres.")
    @NotEmpty(message = "Por favor, informe o nome da conta.")
    private String nome;

    @NotEmpty(message = "Por favor, informe a data da conta.")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", 
             message = "Por favor, informe a data no formato dd/MM/yyyy.")
    private String data;

    @DecimalMin(value = "0.01", message = "Por favor, informe o valor mínimo de '0.01'.")
    @NotNull(message = "Por favor, informe o valor da conta.")
    private Double valor;
    
    @NotEmpty(message = "Por favor, informe o tipo de movimentação da conta.")
    @Pattern(regexp = "^[12]$", message = "O tipo de movimentação deve ser '1' (Receita) ou '2' (Despesa).")
    private String movimentacao;
}




