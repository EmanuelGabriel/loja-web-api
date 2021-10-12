package br.com.emanuelgabriel.lojawebapi.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tb_pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", nullable = false, length = 100)
	private String descricao;

	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@Column(name = "data_pedido", nullable = false)
	private LocalDateTime dataPedido;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itensPedido = new ArrayList<>();

	public Pedido(String descricao, BigDecimal valorTotal, LocalDateTime dataPedido) {
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.dataPedido = dataPedido;
	}

	/**
	 * @author emanuel.sousa 
	 * Método responsável em adicionar um Itens de Pedido no Pedido
	 * @param item
	 */
	public void adicionarItens(ItemPedido item) {
		item.setPedido(this);
		this.itensPedido.add(item);
		// adiciona o valor total
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

}
