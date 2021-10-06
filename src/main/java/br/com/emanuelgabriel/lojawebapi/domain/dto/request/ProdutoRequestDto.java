package br.com.emanuelgabriel.lojawebapi.domain.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDto {

	private String nome;
	private String descricao;
	private BigDecimal preco;
	
	@NotNull
	private CategoriaInputRequestDto categoria;

}
