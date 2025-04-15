package br.com.cotiinformatica.domain.collections;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Document(collection = "log_financas")
@Data
public class LogFinancas {

	@Id
	private UUID id;
	private Instant dataHora;
	private String descricao;
	private Integer idConta;
	private String nomeConta;
	private Date dataConta;
	private Double valorConta;
	private String movimentacao;
}
