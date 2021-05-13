package br.com.elo7.domain.exceptions;

public class SondaNaoEncontradaException extends Exception{

	private static final long serialVersionUID = 1L;
    
    public SondaNaoEncontradaException(String nomeSonda) {
        super(String.format("Não existe uma sonda cadastrada com este nome '%s'", nomeSonda));
    }
}
