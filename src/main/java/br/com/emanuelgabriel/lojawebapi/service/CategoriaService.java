package br.com.emanuelgabriel.lojawebapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.emanuelgabriel.lojawebapi.domain.dto.request.CategoriaRequestDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.CategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.entity.Categoria;
import br.com.emanuelgabriel.lojawebapi.domain.mapper.CategoriaModelMapper;
import br.com.emanuelgabriel.lojawebapi.domain.mapper.GenericMapper;
import br.com.emanuelgabriel.lojawebapi.domain.repository.CategoriaRepository;
import br.com.emanuelgabriel.lojawebapi.service.exception.ObjNaoEncontradoException;
import br.com.emanuelgabriel.lojawebapi.service.exception.RegraNegocioException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaModelMapper mapper;
	
	@Autowired
	private GenericMapper genericMapper;

	public Page<CategoriaResponseDto> buscarTodos(Pageable pageable) {
		log.info("Busca todos as categorias");
		Page<Categoria> categorias = categoriaRepository.findAll(pageable);
		return mapper.mapEntityPageToDTO(pageable, categorias);
	}

	public CategoriaResponseDto criar(CategoriaRequestDto request) {
		log.info("Cria uma nova categoria: {}", request);
		var categoriaExistente = categoriaRepository.findByNome(request.getNome());
		if (categoriaExistente != null && !categoriaExistente.equals(request)) {
			throw new RegraNegocioException("Já existe uma categoria registrada com este nome");
		}

		var categoria = mapper.dtoToEntity(request);
		return genericMapper.paraObjeto(categoriaRepository.save(categoria), CategoriaResponseDto.class); 
	}
	
	public CategoriaResponseDto buscarPorId(Long idCategoria) {
		log.info("Busca categoria por ID: {}", idCategoria);
		Optional<Categoria> categoriaOpt = categoriaRepository.findById(idCategoria);
		if (!categoriaOpt.isPresent()) {
		  throw new ObjNaoEncontradoException("Categoria de ID não encontrado");
		}
		
		return genericMapper.paraObjeto(categoriaOpt.get(), CategoriaResponseDto.class);
	}
}
