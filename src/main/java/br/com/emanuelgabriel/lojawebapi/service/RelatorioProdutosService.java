package br.com.emanuelgabriel.lojawebapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPedidoVODto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.vo.RelatorioVendasPorDiaVODto;
import br.com.emanuelgabriel.lojawebapi.domain.repository.ProdutoRepository;
import br.com.emanuelgabriel.lojawebapi.service.exception.ObjNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RelatorioProdutosService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Object[]> buscarRelatorioVendasProdutosObject() {
		log.info("Buscar relatório de vendas de produtos - object");

		var listaObj = produtoRepository.relatorioVendasProdutosObject();
		if (listaObj.isEmpty()) {
			throw new ObjNaoEncontradoException("Nenhum registro encontrado");
		}

		return listaObj;

	}

	public List<RelatorioVendasPedidoVODto> buscarRelatorioVendasProdutos() {
		log.info("Buscar relatório de vendas de produtos");
		var relatorioVendasProdutos = produtoRepository.relatorioVendasProdutos();
		if (relatorioVendasProdutos.isEmpty()) {
			throw new ObjNaoEncontradoException("Nenhum registro encontrado");
		}
		return relatorioVendasProdutos;
	}

	public List<Object[]> buscarRelatorioVendasProdutosObjectTeste() {
		log.info("Buscar relatório de vendas de produtos - object TESTE");
		var listaObj = produtoRepository.relatorioVendasPorProdutosVendidos();
		if (listaObj.isEmpty()) {
			throw new ObjNaoEncontradoException("Nenhum registro encontrado");
		}
		return listaObj;
	}
	
	public List<RelatorioVendasPorDiaVODto> buscarRelatorioVendasPorDia() {
		log.info("Buscar relatório de vendas de produtos por dia");
		var lista = produtoRepository.relatorioVendasPorDia();
		if (lista.isEmpty()) {
			throw new ObjNaoEncontradoException("Nenhum registro encontrado");
		}

		return lista;
	}

}
