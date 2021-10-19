package br.com.emanuelgabriel.lojawebapi.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.emanuelgabriel.lojawebapi.domain.dto.request.CategoriaRequestDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.CategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.entity.Categoria;

@Component
public class CategoriaModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @author emanuel.sousa
	 * @param entity
	 * @return dto
	 */
	public CategoriaResponseDto entityToDTO(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaResponseDto.class);
	}

	/**
	 * @author emanuel.sousa
	 * @param dto
	 * @return entity
	 */
	public Categoria dtoToEntity(CategoriaResponseDto dto) {
		return this.modelMapper.map(dto, Categoria.class);
	}

	/**
	 * @author emanuel.sousa
	 * @param dto request
	 * @return entity
	 */
	public Categoria dtoToEntity(CategoriaRequestDto dto) {
		return this.modelMapper.map(dto, Categoria.class);
	}

	/**
	 * @author emanuel.sousa
	 * @param categorias
	 * @return List
	 */
	public List<CategoriaResponseDto> listEntityToDTO(List<Categoria> categorias) {
		return categorias.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

    /**
     * @author emanuel.sousa
     * @param categorias
     * @return 
     */
	public List<Categoria> listDtoToEntity(List<CategoriaResponseDto> categorias) {
		return categorias.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}

	public Page<CategoriaResponseDto> mapEntityPageToDTO(Pageable pageable, Page<Categoria> pageCategorias) {
		List<CategoriaResponseDto> listDtos = listEntityToDTO(pageCategorias.getContent());
		return new PageImpl<>(listDtos, pageable, pageCategorias.getTotalElements());
	}

}
