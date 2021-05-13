package br.com.elo7.domain.model;

import br.com.elo7.domain.DirecaoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sonda {
	
	private String nome;
	private int coordenadaX;
	private int coordenadaY;
	private String direcaoCardial;

	
	
	public Sonda(String nome,Integer posicaoInicialX, Integer posicaoIniciaoY, String direcaoCardial) {
		this.nome = nome;
		this.coordenadaX = posicaoInicialX;
		this.coordenadaY = posicaoIniciaoY;
		this.direcaoCardial = direcaoCardial;
	}
	

	public void movimentar() {
		switch (this.direcaoCardial) {
			case "N": 
				this.coordenadaY = this.coordenadaY + 1; break;
			case "S": 
				this.coordenadaY = this.coordenadaY - 1; break;
			case "W": 
				this.coordenadaX = this.coordenadaX - 1; break;
			case "E": 
				this.coordenadaX = this.coordenadaX + 1; break;
		}
	}
	
	
	public void direcao(DirecaoEnum direcao) {	
		
		switch (direcaoCardial) {
		
		case "N": 
			if(isDireita(direcao))
				this.direcaoCardial =  "E";
			else
				this.direcaoCardial = "W";
			break;
		case "E": 
			if(isDireita(direcao))
				this.direcaoCardial = "S";
			else
				this.direcaoCardial = "N";
			break;
		case "S": 
			if(isDireita(direcao))
				this.direcaoCardial = "W";
			else
				this.direcaoCardial =  "E";
			break;
		case "W": 
			if(isDireita(direcao))
				this.direcaoCardial =  "N";
			else
				this.direcaoCardial =  "S";
			break;
		}
	}
	
	
	
	public static boolean isEsquerda(DirecaoEnum movimento) {
		return DirecaoEnum.L.equals(movimento);
	}
	
	public static boolean isDireita(DirecaoEnum movimento) {
		return DirecaoEnum.R.equals(movimento);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coordenadaX;
		result = prime * result + coordenadaY;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sonda other = (Sonda) obj;
		if (coordenadaX != other.coordenadaX)
			return false;
		if (coordenadaY != other.coordenadaY)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	
	
	
}
