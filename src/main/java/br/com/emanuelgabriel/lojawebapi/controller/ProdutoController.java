package br.com.emanuelgabriel.lojawebapi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.emanuelgabriel.lojawebapi.domain.dto.request.ProdutoRequestDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.ProdutoCategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.ProdutoResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.QtdProdutosCategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Slf4j
@RestController
@RequestMapping(value = "/v1/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<Page<ProdutoResponseDto>> buscarTodos(Pageable pageable) {
		log.info("GET /v1/produtos {}", pageable);
		var pageProduto = produtoService.buscarTodos(pageable);
		return pageProduto != null ? ResponseEntity.ok().body(pageProduto) : ResponseEntity.ok().build();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<ProdutoResponseDto> criar(@Valid @RequestBody ProdutoRequestDto request) {
		log.info("POST /v1/produtos - body: '{}'", request);
		var response = produtoService.criar(request);
		URI location = getUri(response.getId());
		return ResponseEntity.created(location).build();
	}

	@GetMapping(value = "{idCategoria}/produtos-por-categoria")
	public ResponseEntity<Page<ProdutoCategoriaResponseDto>> buscarProdutosPorCategoria(@PathVariable Long idCategoria, Pageable pageable ) {
		log.info("GET /v1/produtos/{}/produtos-por-categoria", idCategoria);
		Page<ProdutoCategoriaResponseDto> pageProdutoDto = produtoService.buscarProdutosPorCategoria(idCategoria, pageable);
		return pageProdutoDto != null ? ResponseEntity.ok().body(pageProdutoDto) : ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/produtos-categoria")
	public ResponseEntity<Page<ProdutoCategoriaResponseDto>> buscarProdutosPorCategoria(@RequestParam(value = "nomeProduto") String nomeProduto, @RequestParam(value = "idCategoria") Long idCategoria, Pageable pageable ) {
		log.info("GET /v1/produtos/{}/{}/produtos-categoria", nomeProduto, idCategoria);
		Page<ProdutoCategoriaResponseDto> pageProdutoDto = produtoService.buscarProdutosPorNomeIdCategoria(nomeProduto, idCategoria, pageable);
		return pageProdutoDto != null ? ResponseEntity.ok().body(pageProdutoDto) : ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "{idCategoria}/quantidade-produtos-por-categoria")
	public ResponseEntity<QtdProdutosCategoriaResponseDto> buscarQuantidadeProdutosPorCategoria(@PathVariable Long idCategoria) {
		log.info("GET /v1/produtos/{}/quantidade-produtos-por-categoria", idCategoria);
		QtdProdutosCategoriaResponseDto quantidade = produtoService.quantidadeProdutosPorCategoria(idCategoria);
		return ResponseEntity.ok(quantidade);
	}
	

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

}
