package br.com.emanuelgabriel.lojawebapi.domain.repository.customers.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPedidoVODto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPorDiaVODto;
import br.com.emanuelgabriel.lojawebapi.domain.repository.customers.ProdutoRepositoryCustom;

public class ProdutoRepositoryCustomImpl implements ProdutoRepositoryCustom {

	@Autowired
	private EntityManager em;
	
	/**
	 * @author emanuel.sousa
	 * @return object[]
	 */
	@Override
	public List<Object[]> relatorioVendasProdutosObject(){
		
		String jpql = "SELECT produto.nome, SUM(item.quantidade), MAX(pedido.dataPedido) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itensPedido item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		
		return em.createQuery(jpql, Object[].class).getResultList();
	}
	
	/**
	 * @author emanuel.sousa
	 * @return List<RelatorioVendasPedidoVODto>
	 */
	@Override
	public List<RelatorioVendasPedidoVODto> relatorioVendasProdutos(){
		
		String jpql = "SELECT new br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPedidoVODto(produto.nome, SUM(item.quantidade), MAX(pedido.dataPedido)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itensPedido item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		
		
		return em.createQuery(jpql, RelatorioVendasPedidoVODto.class).getResultList();
	}

	@Override
	public List<RelatorioVendasPorDiaVODto> relatorioVendasPorDia() {
		String jpql = "SELECT new br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPorDiaVODto(prod.nome, SUM(ped.valorTotal), ped.dataPedido) FROM Pedido ped JOIN ped.itensPedido itens JOIN itens.produto prod GROUP BY prod.nome, ped.dataPedido";
		
		return em.createQuery(jpql, RelatorioVendasPorDiaVODto.class).getResultList();
	}
	
}
