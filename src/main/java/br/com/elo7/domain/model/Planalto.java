package br.com.elo7.domain.model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Planalto {
	
	private Shape borda = new Rectangle2D.Float();
	
	private Set<Sonda> sondas = new HashSet<Sonda>();
	
	
	public Planalto(int width, int heigth) {
		
		this.borda = new Rectangle2D.Float(0, 0, width, heigth);
	}
	
    public void addSonda(Sonda sonda) {    
    	sondas.add(sonda);
    }
}
