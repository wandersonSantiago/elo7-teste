package br.com.elo7.api.exceptions;

import lombok.Getter;

@Getter
public enum ProblemType {

	DADOS_INVALIDOS("dados-invalidos", "Dados inválidos"),
	ERRO_DE_SISTEMA("erro-de-sistema", "Erro de sistema"),
	PARAMETRO_INVALIDO("parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("erro-negocio", "Violação de regra de negócio");
	
	public String title;
	public String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://elo7.com/desafio/" + path;
		this.title = title;
	}

	
}
