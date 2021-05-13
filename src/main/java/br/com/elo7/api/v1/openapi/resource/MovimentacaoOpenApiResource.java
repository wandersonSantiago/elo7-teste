package br.com.elo7.api.v1.openapi.resource;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.parsing.Problem;

import br.com.elo7.api.v2.dto.ConsultarPlanaltoDto;
import br.com.elo7.domain.exceptions.ColisaoEntreSondasException;
import br.com.elo7.domain.exceptions.SondaEstourouABordaException;
import br.com.elo7.domain.exceptions.SondaNaoEncontradaException;
import br.com.elo7.domain.model.Sonda;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Sondas", consumes = "APPLICATION_JSON", description =  "Versão 1 da Api Explorando Marte")
public interface MovimentacaoOpenApiResource {
	
	
	@ApiOperation("Cria duas sondas e as direciona no planalto")
	@ApiResponses({
		@ApiResponse(code = 409, message = "Conflito entre Sondas", response = Problem.class),
		@ApiResponse(code = 404, message = "Sonda não encontrada", response = Problem.class)
	})
	ConsultarPlanaltoDto movimentacao() throws SondaNaoEncontradaException, SondaEstourouABordaException, ColisaoEntreSondasException;

	@ApiOperation("Cria um plano e sondas a partir de uma arquivo txt que fica na razi do projeto")
	@ApiResponses({
		@ApiResponse(code = 409, message = "Conflito entre Sondas", response = Problem.class),
		@ApiResponse(code = 404, message = "Sonda não encontrada", response = Problem.class)
	})
	Set<Sonda> gerarMovimentacaoAPartirDeUmArquivo() throws IOException, SondaEstourouABordaException;
}
