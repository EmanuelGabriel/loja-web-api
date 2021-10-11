package br.com.emanuelgabriel.lojawebapi.domain.dto.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioVendasPedidoVODto {

	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDateTime dataUltimaVenda;

}
