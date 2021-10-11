package br.com.emanuelgabriel.lojawebapi.domain.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.emanuelgabriel.lojawebapi.domain.dto.request.ProdutoRequestDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.ProdutoCategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.dto.response.ProdutoResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.entity.Produto;

/**
 * 
 * @author emanuel.sousa
 *
 */

@Component
public class ProdutoModelMapper {

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @author emanuel.sousa
	 * @param entity
	 * @return dto
	 */
	public ProdutoResponseDto entityToDTO(Produto entity) {
		return modelMapper.map(entity, ProdutoResponseDto.class);
	}
	
	

	/**
	 * @author emanuel.sousa
	 * @param entity
	 * @return dto
	 */
	public ProdutoCategoriaResponseDto entityToDto(Produto entity) {
		return modelMapper.map(entity, ProdutoCategoriaResponseDto.class);
	}

	/**
	 * @author emanuel.sousa
	 * @param dto
	 * @return entity
	 */
	public Produto dtoToEntity(ProdutoResponseDto dto) {
		return this.modelMapper.map(dto, Produto.class);
	}

	/**
	 * @author emanuel.sousa
	 * @param request
	 * @return entity
	 */
	public Produto dtoToEntity(ProdutoRequestDto request) {
		return this.modelMapper.map(request, Produto.class);
	}

	/**
	 * @author emanuel.sousa
	 * @param produtos
	 * @return list dto
	 */
	public List<ProdutoResponseDto> listEntityToDTO(List<Produto> produtos) {
		return produtos.stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	/**
	 * @author emanuel.sousa
	 * @param produtos
	 * @return dto
	 */
	public List<ProdutoCategoriaResponseDto> listEntityToDto(List<Produto> produtos) {
		return produtos.stream().map(this::entityToDto).collect(Collectors.toList());
	}

	/**
	 * @author emanuel.sousa
	 * @param produtos
	 * @return list entity
	 */
	public List<Produto> listDtoToEntity(List<ProdutoResponseDto> produtos) {
		return produtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}

	/**
	 * @author emanuel.sousa
	 * @param pageable
	 * @param pageProduto
	 * @return page dto
	 */
	public Page<ProdutoResponseDto> mapEntityPageToDTO(Pageable pageable, Page<Produto> pageProduto) {
		List<ProdutoResponseDto> listDtos = listEntityToDTO(pageProduto.getContent());
		return new PageImpl<>(listDtos, pageable, pageProduto.getTotalElements());
	}

	public Page<ProdutoCategoriaResponseDto> mapEntityPageToPageDTO(Pageable pageable, Page<Produto> pageProduto) {
		List<ProdutoCategoriaResponseDto> listDtos = mapListEntityToDTO(pageProduto.getContent());
		return new PageImpl<>(listDtos, pageable, pageProduto.getTotalElements());
	}

	public List<ProdutoCategoriaResponseDto> mapListEntityToDTO(List<Produto> produtos) {
		return produtos.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
	}

	public ProdutoCategoriaResponseDto mapEntityToDTO(Produto entity) {
		return modelMapper.map(entity, ProdutoCategoriaResponseDto.class);
	}
	

	/**
	 * @author emanuel.sousa
	 * @param pageable
	 * @param pageProduto
	 * @return dto
	 */
	public Page<ProdutoCategoriaResponseDto> mapEntityPageToDto(Pageable pageable, Page<Produto> pageProduto) {
		List<ProdutoCategoriaResponseDto> listDtos = listEntityToDto(pageProduto.getContent());
		return new PageImpl<>(listDtos, pageable, pageProduto.getTotalElements());
	}

	
	
}
