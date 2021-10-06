package br.com.emanuelgabriel.lojawebapi.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 40)
	private String nome;

	@Column(name = "descricao", length = 50)
	private String descricao;

	@Column(name = "preco", precision = 19, scale = 2)
	private BigDecimal preco;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

	@ManyToOne
	private Categoria categoria;

}
