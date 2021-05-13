package br.com.elo7.api.v2.openapi;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.elo7.api.v2.dto.ConsultarSondaDto;
import br.com.elo7.api.v2.dto.CriarSondaDto;
import br.com.elo7.domain.DirecaoEnum;
import br.com.elo7.domain.exceptions.ColisaoEntreSondasException;
import br.com.elo7.domain.exceptions.SondaEstourouABordaException;
import br.com.elo7.domain.exceptions.SondaNaoEncontradaException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(description = "Versão 2 da Api Explorando Marte", name = "Sondas")
public interface SondaOpenApiResource {

	
	@ApiOperation("Cria uma sonda")
	@ApiResponses({
		@ApiResponse(code = 409, message = "Conflito entre Sondas", response = Problem.class),
		@ApiResponse(code = 409, message = "Sonda atingiu uma borda", response = Problem.class)
	})
	public ConsultarSondaDto criarSonda(CriarSondaDto sonda) throws SondaEstourouABordaException, ColisaoEntreSondasException;
	
	
	@ApiOperation("Deleta uma sonda a partir do nome")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Sonda não encontrada", response = Problem.class)
	})
	public void deletarSonda(String nome);

	@ApiOperation("Direciona a sonda passando o nome e a direcao")
	@ApiResponses({
		@ApiResponse(code = 409, message = "Conflito entre Sondas", response = Problem.class),
		@ApiResponse(code = 404, message = "Sonda não encontrada", response = Problem.class),
		@ApiResponse(code = 409, message = "Sonda atingiu uma borda", response = Problem.class)
	})
	public ConsultarSondaDto direcionarSonda(String nome, @PathVariable DirecaoEnum direcao) throws SondaNaoEncontradaException, SondaEstourouABordaException, ColisaoEntreSondasException;

	@ApiOperation("Movimenta a Sonda na direção que ela esta apontando")
	@ApiResponses({
		@ApiResponse(code = 409, message = "Conflito entre Sondas", response = Problem.class),
		@ApiResponse(code = 404, message = "Sonda não encontrada", response = Problem.class),
		@ApiResponse(code = 409, message = "Sonda atingiu uma borda", response = Problem.class)
	})
	public ConsultarSondaDto movimentarSonda(String nome) throws SondaNaoEncontradaException, SondaEstourouABordaException, ColisaoEntreSondasException;
	
}
