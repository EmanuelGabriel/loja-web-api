package br.com.emanuelgabriel.lojawebapi.domain.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaInputRequestDto {

	@NotNull
	private Long id;

}
