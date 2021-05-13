package br.com.elo7.api.v2.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.api.v2.dto.ConsultarSondaDto;
import br.com.elo7.api.v2.dto.CriarSondaDto;
import br.com.elo7.api.v2.hateoas.Elo7LinksV2;
import br.com.elo7.api.v2.openapi.SondaOpenApiResource;
import br.com.elo7.domain.DirecaoEnum;
import br.com.elo7.domain.exceptions.ColisaoEntreSondasException;
import br.com.elo7.domain.exceptions.SondaEstourouABordaException;
import br.com.elo7.domain.exceptions.SondaNaoEncontradaException;
import br.com.elo7.domain.model.Sonda;
import br.com.elo7.domain.service.SondaService;

@RestController
@RequestMapping(value = "/v2/sondas")
public class SondaResource implements SondaOpenApiResource{
	
	@Autowired
	private SondaService sondaService;
	@Autowired
	private Elo7LinksV2 link;
	
	@PostMapping
	public ConsultarSondaDto criarSonda(@Valid @RequestBody CriarSondaDto dto) throws SondaEstourouABordaException, ColisaoEntreSondasException {	
		var sonda = new Sonda(dto.getNome(), dto.getCoordenadaX(), dto.getCoordenadaY(), dto.getDirecaoCardial());
		var sondaCriada = sondaService.criarSonda(sonda);
		
		var sondaDto = converteSondaEmDto(sondaCriada);
		
		sondaDto.add(link.linkToEntradas(sondaDto.getNome()));
		
		return sondaDto;
	}
	
	@DeleteMapping("/nome/{nome}")
	public void deletarSonda(@PathVariable String nome) {
		sondaService.deletar(nome);
	}

	@PutMapping("/direcionar/nome/{nome}/direcao/{direcao}")
	public ConsultarSondaDto direcionarSonda(@PathVariable String nome, @PathVariable DirecaoEnum direcao) throws SondaNaoEncontradaException, SondaEstourouABordaException, ColisaoEntreSondasException {
		var sonda = sondaService.direcionar(nome, direcao);
		return converteSondaEmDto(sonda);
	}

	@PutMapping("/movimentar/nome/{nome}")
	public ConsultarSondaDto movimentarSonda(@PathVariable String nome) throws SondaNaoEncontradaException, SondaEstourouABordaException, ColisaoEntreSondasException {
		var sonda = sondaService.movimentar(nome);
		return converteSondaEmDto(sonda);
	}
	
	@GetMapping("/nome/{nome}")
	public ConsultarSondaDto consultarSondaPeloNome(@PathVariable String nome) throws SondaNaoEncontradaException {
		var sonda = sondaService.buscarSondaPeloNome(nome);
		return converteSondaEmDto(sonda);
	}
	
	private ConsultarSondaDto converteSondaEmDto(Sonda sonda) {
		return new ConsultarSondaDto(
				sonda.getNome(),
				sonda.getCoordenadaX(), 
				sonda.getCoordenadaY(), 
				sonda.getDirecaoCardial()
				);
	}
	
}
