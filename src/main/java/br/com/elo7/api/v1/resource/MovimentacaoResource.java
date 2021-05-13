package br.com.elo7.api.v1.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.api.v1.openapi.resource.MovimentacaoOpenApiResource;
import br.com.elo7.api.v2.dto.ConsultarPlanaltoDto;
import br.com.elo7.domain.DirecaoEnum;
import br.com.elo7.domain.exceptions.ColisaoEntreSondasException;
import br.com.elo7.domain.exceptions.SondaEstourouABordaException;
import br.com.elo7.domain.exceptions.SondaNaoEncontradaException;
import br.com.elo7.domain.model.Planalto;
import br.com.elo7.domain.model.Sonda;
import br.com.elo7.domain.service.PlanaltoService;
import br.com.elo7.domain.service.SondaService;

@RestController
@RequestMapping(value = "/v1/sondas")
public class MovimentacaoResource implements MovimentacaoOpenApiResource{
	
	@Autowired
	private PlanaltoService planaltoService;
	@Autowired
	private SondaService sondaService;
	
	
	@GetMapping
	public Set<Sonda> gerarMovimentacaoAPartirDeUmArquivo() throws IOException, SondaEstourouABordaException {
		
		Iterator<String> dadosDoArquivo = Files.readAllLines(Paths.get("dadosPlanoESonda.txt")).iterator();
		
		String larguraAlturaPlano[] = dadosDoArquivo.next().split("\\s");
						
		Planalto planalto = new Planalto(Integer.valueOf(larguraAlturaPlano[0]) + 1, Integer.valueOf(larguraAlturaPlano[1]) +1);

		while (dadosDoArquivo.hasNext()) {
			
			String[] dadosSonda = dadosDoArquivo.next().split("\\s");

			Sonda sonda = new Sonda(UUID.randomUUID().toString(), Integer.valueOf(dadosSonda[0]), Integer.valueOf(dadosSonda[1]), dadosSonda[2]);

			String movimentoSonda = dadosDoArquivo.next();
			
			for(Character item : movimentoSonda.toCharArray()) {
				if("M".contains(item.toString())) 
					sonda.movimentar();					
				else
					sonda.direcao(DirecaoEnum.valueOf(item.toString()));
			}
			
			planalto.addSonda(sonda);
		}
		return planalto.getSondas();
		
	}
	
	    		
	
	@GetMapping("/movimentar")
	public ConsultarPlanaltoDto movimentacao() throws SondaNaoEncontradaException, SondaEstourouABordaException, ColisaoEntreSondasException {
		
		
		planaltoService.criar(6, 6);
		
		Sonda sondaNasa = new Sonda("Nasa", 1,2, "N");
		
		sondaService.criarSonda(sondaNasa);
		
		sondaService.direcionar(sondaNasa.getNome(), DirecaoEnum.L);
		sondaService.movimentar(sondaNasa.getNome());
		
		sondaService.direcionar(sondaNasa.getNome(), DirecaoEnum.L);
		sondaService.movimentar(sondaNasa.getNome());
		
		sondaService.direcionar(sondaNasa.getNome(), DirecaoEnum.L);
		sondaService.movimentar(sondaNasa.getNome());
		
		sondaService.direcionar(sondaNasa.getNome(), DirecaoEnum.L);
		sondaService.movimentar(sondaNasa.getNome());
		
		sondaService.movimentar(sondaNasa.getNome());

		
		Sonda spaceX = new Sonda("spaceX", 3,3, "E");
		
		sondaService.criarSonda(spaceX);
		
		sondaService.movimentar(spaceX.getNome());
		sondaService.movimentar(spaceX.getNome());
		sondaService.direcionar(spaceX.getNome(), DirecaoEnum.R);
		sondaService.movimentar(spaceX.getNome());
		sondaService.movimentar(spaceX.getNome());
		sondaService.direcionar(spaceX.getNome(), DirecaoEnum.R);
		sondaService.movimentar(spaceX.getNome());
		sondaService.direcionar(spaceX.getNome(), DirecaoEnum.R);
		sondaService.direcionar(spaceX.getNome(), DirecaoEnum.R);
		sondaService.movimentar(spaceX.getNome());
		
		
		 var planalto = planaltoService.consultar();
		 
		 return new ConsultarPlanaltoDto(
					planalto.getBorda().getBounds().getHeight() ,
					planalto.getBorda().getBounds().getHeight(), 
					planalto.getSondas()); 
	}

}
