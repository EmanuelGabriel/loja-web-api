package br.com.emanuelgabriel.lojawebapi.domain.repository.customers;

import java.util.List;

import br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPedidoVODto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPorDiaVODto;

public interface ProdutoRepositoryCustom {

	List<Object[]> relatorioVendasProdutosObject();

	List<RelatorioVendasPedidoVODto> relatorioVendasProdutos();

	List<RelatorioVendasPorDiaVODto> relatorioVendasPorDia();
}
