package br.com.emanuelgabriel.lojawebapi.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.emanuelgabriel.lojawebapi.domain.dto.response.QtdProdutosCategoriaResponseDto;
import br.com.emanuelgabriel.lojawebapi.domain.entity.Produto;
import br.com.emanuelgabriel.lojawebapi.domain.repository.customers.ProdutoRepositoryCustom;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryCustom {

//	@Query(value = "SELECT p FROM Produto p JOIN FETCH p.categoria c WHERE c.id = :idCategoria")
//	Page<Produto> buscarProdutosPorCategoria(@Param(value = "idCategoria") Long idCategoria, Pageable pageable);

	Page<Produto> findByCategoriaId(Long idCategoria, Pageable pageable);

	Page<Produto> findByNomeContainingIgnoreCaseAndCategoriaId(String nomeProduto, Long idCategoria, Pageable pageable);

	Integer countByCategoriaId(Long categoriaID);

	@Query(value = "SELECT cat.nome AS nomeCategoria, COUNT(cat.id) AS quantidade FROM tb_produto p LEFT JOIN tb_categoria cat ON p.categoria_id = cat.id WHERE cat.id = :idCategoria", nativeQuery = true)
	QtdProdutosCategoriaResponseDto buscarQuantidadeProdutosPorCategoria(@Param("idCategoria") Long idCategoria);

	@Query(value = "SELECT prod.nome, SUM(item.quantidade), MAX(ped.dataPedido) FROM Pedido ped JOIN ped.itensPedido item JOIN item.produto prod GROUP BY prod.nome ORDER BY item.quantidade DESC")
	List<Object[]> relatorioVendasPorProdutosVendidos();
	
	// SELECT new br.com.alura.loja.dto.VendasPorDia(produto.nome, sum(pedido.valorTotal), pedido.data) 
	// FROM Pedido pedido join pedido.itens itens join itens.produto produto group by produto.nome, pedido.data

}
