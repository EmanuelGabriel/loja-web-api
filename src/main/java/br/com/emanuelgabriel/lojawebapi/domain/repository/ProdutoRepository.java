package br.com.emanuelgabriel.lojawebapi.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.emanuelgabriel.lojawebapi.domain.dto.response.QtdProdutosCategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//	@Query(value = "SELECT p FROM Produto p JOIN FETCH p.categoria c WHERE c.id = :idCategoria")
//	Page<Produto> buscarProdutosPorCategoria(@Param(value = "idCategoria") Long idCategoria, Pageable pageable);

	Page<Produto> findByCategoriaId(Long idCategoria, Pageable pageable);
	
	Page<Produto> findByNomeContainingIgnoreCaseAndCategoriaId(String nomeProduto, Long idCategoria, Pageable pageable);
	
	Integer countByCategoriaId(Long categoriaID);
	
	 @Query(value = "SELECT cat.nome AS nomeCategoria, COUNT(cat.id) AS quantidade FROM tb_produto p LEFT JOIN tb_categoria cat ON p.categoria_id = cat.id WHERE cat.id = :idCategoria", nativeQuery = true)
     QtdProdutosCategoriaResponseDto buscarQuantidadeProdutosPorCategoria(@Param("idCategoria") Long idCategoria);

}
