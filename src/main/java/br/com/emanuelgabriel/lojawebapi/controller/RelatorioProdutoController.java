package br.com.emanuelgabriel.lojawebapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPedidoVODto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPorDiaVODto;
import br.com.emanuelgabriel.lojawebapi.service.RelatorioProdutosService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Slf4j
@RestController
@RequestMapping(value = "/v1/relatorios", produces = MediaType.APPLICATION_JSON_VALUE)
public class RelatorioProdutoController {

	@Autowired
	private RelatorioProdutosService service;

	@GetMapping(value = "/produtos-vendidos-teste")
	public ResponseEntity<List<Object[]>> buscarRelatorioVendasProdutosObj() {
		log.info("GET /v1/relatorios/produtos-teste");
		List<Object[]> objRelatorio = service.buscarRelatorioVendasProdutosObject();
		return ResponseEntity.ok().body(objRelatorio);
	}

	@GetMapping(value = "/produtos-vendidos")
	public ResponseEntity<List<RelatorioVendasPedidoVODto>> buscarRelatorioVendasPorProdutos() {
		log.info("GET /v1/relatorios/produtos-vendidos");
		List<RelatorioVendasPedidoVODto> dtoRelatorio = service.buscarRelatorioVendasProdutos();
		return ResponseEntity.ok().body(dtoRelatorio);
	}
	
	@GetMapping(value = "/vendas-por-dia")
	public ResponseEntity<List<RelatorioVendasPorDiaVODto>> buscarRelatorioVendasPorProdutosPorDia() {
		log.info("GET /v1/relatorios/vendas-por-dia");
		List<RelatorioVendasPorDiaVODto> dtoRelatorio = service.buscarRelatorioVendasPorDia();
		return ResponseEntity.ok().body(dtoRelatorio);
	}

}
