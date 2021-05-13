package br.com.elo7.api.v2.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarPlanaltoDto {
	
	@NotNull
	private Integer largura;
	@NotNull
	private Integer altura;

}
