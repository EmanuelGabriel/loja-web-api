package br.com.emanuelgabriel.lojawebapi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.emanuelgabriel.lojawebapi.domain.dto.request.CategoriaRequestDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.CategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.service.CategoriaService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Slf4j
@RestController
@RequestMapping(value = "/v1/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<Page<CategoriaResponseDto>> buscarTodos(Pageable pageable) {
		log.info("GET /v1/categorias {}", pageable);
		var categorias = categoriaService.buscarTodos(pageable);
		return categorias != null ? ResponseEntity.ok().body(categorias) : ResponseEntity.ok().build();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<CategoriaResponseDto> criar(@RequestBody CategoriaRequestDto request) {
		log.info("POST /v1/categorias {}", request);
		var categoriaResponse = categoriaService.criar(request);
		URI location = getUri(categoriaResponse.getId());
		return ResponseEntity.created(location).build();
	}

	/**
	 * @author emanuel.sousa
	 * @param id
	 * @return URI
	 */
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
