package br.com.emanuelgabriel.lojawebapi.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.emanuelgabriel.lojawebapi.domain.dto.request.ProdutoRequestDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.ProdutoCategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.ProdutoResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.QtdProdutosCategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.entity.Produto;
import br.com.emanuelgabriel.lojawebapi.domain.mapper.ProdutoModelMapper;
import br.com.emanuelgabriel.lojawebapi.domain.repository.CategoriaRepository;
import br.com.emanuelgabriel.lojawebapi.domain.repository.ProdutoRepository;
import br.com.emanuelgabriel.lojawebapi.service.exception.ObjNaoEncontradoException;
import br.com.emanuelgabriel.lojawebapi.service.exception.RegraNegocioException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Slf4j
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoModelMapper mapper;

	public Page<ProdutoCategoriaResponseDto> buscarProdPorCategorias(Long idCategoria, Pageable pageable) {
		log.info("TESTE {}-{}", idCategoria, pageable);
		var pageProduto = produtoRepository.buscarProdutosPorCategoria(idCategoria, pageable);
		if (pageProduto.isEmpty()) {
			throw new ObjNaoEncontradoException("Nenhum registro encontrado");
		}
		return mapper.mapEntityPageToPageDTO(pageable, pageProduto);
	}
	
	public Page<ProdutoResponseDto> buscarTodos(Pageable pageable) {
		log.info("Busca todos os produtos cadastrados: {}", pageable);
		Page<Produto> pageProduto = produtoRepository.buscarTodos(pageable); //findAll(pageable);
		return mapper.mapEntityPageToDTO(pageable, pageProduto);
	}

	public ProdutoResponseDto criar(ProdutoRequestDto request) {
		log.info("Criar produto : {}", request);
		var categoria = categoriaRepository.findById(request.getCategoria().getId());
		if (!categoria.isPresent()) {
			throw new RegraNegocioException("Categoria de código não encontrado");
		}

		var produto = mapper.dtoToEntity(request);
		produto.setDataCadastro(LocalDate.now());
		produto.setCategoria(categoria.get());

		return mapper.entityToDTO(produtoRepository.save(produto));
	}

	public Page<ProdutoCategoriaResponseDto> buscarProdutosPorNomeIdCategoria(String nomeProduto, Long idCategoria,
			Pageable pageable) {
		log.info("Busca produtos por nome e id da categoria: '{}';'{}'", nomeProduto, idCategoria);
		Page<Produto> pageProduto = produtoRepository.findByNomeContainingIgnoreCaseAndCategoriaId(nomeProduto,
				idCategoria, pageable);
		if (pageProduto.isEmpty()) {
			throw new ObjNaoEncontradoException("Nenhum registro encontrado");
		}
		return mapper.mapEntityPageToDto(pageable, pageProduto);
	}

	public Page<ProdutoCategoriaResponseDto> buscarProdutosPorCategoria(Long idCategoria, Pageable pageable) {
		log.info("Busca produtos por categoria de código: '{}'", idCategoria);

		Page<Produto> pageProduto = produtoRepository.findByCategoriaId(idCategoria, pageable);

		if (pageProduto.isEmpty()) {
			throw new ObjNaoEncontradoException("Nenhum produto encontrado pra esta categoria");
		}

		return mapper.mapEntityPageToDto(pageable, pageProduto);
	}

	public QtdProdutosCategoriaResponseDto quantidadeProdutosPorCategoria(Long idCategoria) {
		log.info("Busca a quantidade de produtos por categoria ID da Categoria: '{}'", idCategoria);
		return produtoRepository.buscarQuantidadeProdutosPorCategoria(idCategoria);
	}

}
