package br.com.elo7.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.domain.DirecaoEnum;
import br.com.elo7.domain.exceptions.ColisaoEntreSondasException;
import br.com.elo7.domain.exceptions.SondaEstourouABordaException;
import br.com.elo7.domain.exceptions.SondaNaoEncontradaException;
import br.com.elo7.domain.model.Sonda;

@Service
public class SondaService {
	
	
	@Autowired
	private PlanaltoService planaltoService;
	
	
	public Sonda criarSonda(Sonda sonda) throws SondaEstourouABordaException, ColisaoEntreSondasException {
		
		planaltoService.isTocouABorda(sonda);
		planaltoService.isColidiu(sonda);
		planaltoService.addSonda(sonda);
		
		return sonda;
	}
	
	public Sonda direcionar(String nome, DirecaoEnum direcao) throws SondaNaoEncontradaException, SondaEstourouABordaException, ColisaoEntreSondasException {
		
		Sonda sonda =  buscarSondaPeloNome(nome);
		
		sonda.direcao(direcao);
	
		return sonda;
		
	}
	
	public Sonda movimentar(String nome) throws SondaNaoEncontradaException, SondaEstourouABordaException, ColisaoEntreSondasException {
		
		var sonda = buscarSondaPeloNome(nome);
		
		var x = sonda.getCoordenadaX();
		var y = sonda.getCoordenadaY();
		
		sonda.movimentar();
		
		if(planaltoService.isColidiu(sonda)) {
			sonda.setCoordenadaX(x);
			sonda.setCoordenadaY(y);
			throw new ColisaoEntreSondasException(sonda.getNome());
		}
		if(planaltoService.isTocouABorda(sonda)) {
			sonda.setCoordenadaX(x);
			sonda.setCoordenadaY(y);
			throw new SondaEstourouABordaException(sonda.getNome());
		}
		
		return sonda;
		
	}
	
	
	public Sonda buscarSondaPeloNome(String nome) throws SondaNaoEncontradaException {
		Sonda sonda = planaltoService.buscarSondaPeloNome(nome);
		
		if(sonda == null)
			throw new SondaNaoEncontradaException(nome);
		return sonda;		
	}

	public void deletar(String nome) {
		planaltoService.removeSonda(nome);
	}


}
