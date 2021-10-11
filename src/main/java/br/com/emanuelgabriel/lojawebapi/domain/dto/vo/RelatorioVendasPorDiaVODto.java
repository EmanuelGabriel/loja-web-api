package br.com.emanuelgabriel.lojawebapi.domain.dto.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioVendasPorDiaVODto {

	private String nomeDoProduto;
	private BigDecimal valorVendido;
	private LocalDateTime dataDasVendas;

}
