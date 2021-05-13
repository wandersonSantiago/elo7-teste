package br.com.elo7.api.v2.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.elo7.domain.model.Sonda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultarPlanaltoDto {
	
	private Double largura;
	private Double altura;
	
	private Set<Sonda> sondas = new HashSet<Sonda>();

	
}
