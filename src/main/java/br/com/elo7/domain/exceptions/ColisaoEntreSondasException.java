package br.com.elo7.domain.exceptions;

public class ColisaoEntreSondasException extends Exception{

	private static final long serialVersionUID = 1L;
    
    public ColisaoEntreSondasException(String nomeSonda) {
        super(String.format("Não foi possivel movimentar a sonda '%s' para esta posição, perigo de colisão!", nomeSonda));
    }
}
