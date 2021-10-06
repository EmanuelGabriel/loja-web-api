package br.com.emanuelgabriel.lojawebapi.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDto {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCadastro;
	private CategoriaResponseDto categoria;
}
