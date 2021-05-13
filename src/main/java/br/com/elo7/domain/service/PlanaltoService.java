package br.com.elo7.domain.service;

import org.springframework.stereotype.Service;

import br.com.elo7.domain.exceptions.ColisaoEntreSondasException;
import br.com.elo7.domain.exceptions.SondaEstourouABordaException;
import br.com.elo7.domain.model.Planalto;
import br.com.elo7.domain.model.Sonda;

@Service
public class PlanaltoService {

	private Planalto planalto;

	public PlanaltoService() {
		if (planalto == null)
			planalto = new Planalto(5, 5);
	}

	public void criar(Integer largura, Integer altura) {

		planalto = new Planalto(largura, altura);
	}

	public Planalto consultar() {
		return planalto;
	}

	public Boolean isTocouABorda(Sonda sonda) throws SondaEstourouABordaException {

		if (!planalto.getBorda().contains(sonda.getCoordenadaX() == 0 ? 0 : sonda.getCoordenadaX() - 1,
				sonda.getCoordenadaY() == 0 ? 0 : sonda.getCoordenadaY() - 1)) {
			return true;
		}
		return false;
	}

	public Boolean isColidiu(Sonda sonda) throws ColisaoEntreSondasException, SondaEstourouABordaException {

		var count = planalto.getSondas().stream()
				.filter(s -> s.getCoordenadaX() == sonda.getCoordenadaX()
						&& s.getCoordenadaY() == sonda.getCoordenadaY() && !s.getNome().contentEquals(sonda.getNome())).count();
		if (count > 0) 
			return true;
		return false;
	}

	public void addSonda(Sonda sonda) {
		planalto.addSonda(sonda);
	}

	public void removeSonda(String nome) {
		planalto.getSondas().removeIf(sonda -> sonda.getNome().equalsIgnoreCase(nome));
	}

	public Sonda buscarSondaPeloNome(String nome) {
		return planalto.getSondas().stream().filter(s -> s.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
	}

}
