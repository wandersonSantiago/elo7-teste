package br.com.elo7.api.v2.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConsultarSondaDto extends RepresentationModel<ConsultarSondaDto> {
	
	private String nome;
	private int coordenadaX;
	private int coordenadaY;
	private String direcaoCardial;

	
}
