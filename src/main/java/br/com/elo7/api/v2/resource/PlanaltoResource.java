package br.com.elo7.api.v2.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.api.v2.dto.ConsultarPlanaltoDto;
import br.com.elo7.api.v2.dto.CriarPlanaltoDto;
import br.com.elo7.api.v2.openapi.PlanaltoOpenApiResource;
import br.com.elo7.domain.service.PlanaltoService;

@RestController
@RequestMapping(value = "/v2/planaltos")
public class PlanaltoResource implements PlanaltoOpenApiResource{
	
	@Autowired
	private PlanaltoService service;

	@PostMapping
	public void criar(@Valid @RequestBody CriarPlanaltoDto dto) {		
		 service.criar(dto.getLargura(), dto.getAltura());
	}
	
	@GetMapping
	public ConsultarPlanaltoDto consultar() {		
		var planalto = service.consultar();
		
		return new ConsultarPlanaltoDto(
				planalto.getBorda().getBounds().getHeight() ,
				planalto.getBorda().getBounds().getHeight(), 
				planalto.getSondas()); 
	}

}
