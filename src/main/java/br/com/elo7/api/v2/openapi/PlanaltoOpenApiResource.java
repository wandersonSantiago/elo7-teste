package br.com.elo7.api.v2.openapi;

import org.springframework.beans.factory.parsing.Problem;

import br.com.elo7.api.v2.dto.ConsultarPlanaltoDto;
import br.com.elo7.api.v2.dto.CriarPlanaltoDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(description = "Versão 2 da Api Explorando Marte", name = "Planalto")
public interface PlanaltoOpenApiResource {
	
	
	@ApiOperation("Este end-point cria um plano definindo seu tamanho por largura e altura")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Atributos largura e altura obrigatórios!", response = Problem.class)
	})
	void criar(CriarPlanaltoDto dto);
	
	@ApiOperation("Consulta o tamanho do planalto e suas sondas")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Plano não encontrado!", response = Problem.class)
	})
	ConsultarPlanaltoDto consultar();

}
