package br.com.elo7.api.v2.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarSondaDto {
	
	@NotBlank
	private String nome;
	@NotNull
	private int coordenadaX;
	@NotNull
	private int coordenadaY;
	@NotBlank
	private String direcaoCardial;

	
}
