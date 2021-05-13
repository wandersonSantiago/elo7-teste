package br.com.elo7.domain.exceptions;

public class SondaEstourouABordaException extends Exception{

	private static final long serialVersionUID = 1L;
    
    public SondaEstourouABordaException(String nomeSonda) {
        super(String.format("Não foi possivel movimentar a sonda '%s' para esta posição, ela irá bater na borda! ", nomeSonda));
    }
}
