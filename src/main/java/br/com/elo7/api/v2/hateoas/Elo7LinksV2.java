package br.com.elo7.api.v2.hateoas;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.elo7.api.v2.resource.SondaResource;

@Component
public class Elo7LinksV2 {

	
	public Link linkToEntradas(String nome) {
		return linkTo(nome, IanaLinkRelations.SELF.value());
	}

	private Link linkTo(String nome, String value) {
		return WebMvcLinkBuilder.linkTo(SondaResource.class)
				.slash("nome/" + nome).withSelfRel();
	}
}
