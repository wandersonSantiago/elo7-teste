package br.com.elo7.api.v1.hateoas;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class Elo7Links {

	
	
	
	public Link linkToEntradas() {
		return linkTo(IanaLinkRelations.SELF.value());
	}

	private Link linkTo(String value) {
		return null;
	}
}
