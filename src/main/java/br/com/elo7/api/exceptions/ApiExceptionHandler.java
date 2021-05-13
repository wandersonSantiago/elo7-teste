package br.com.elo7.api.exceptions;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.elo7.domain.exceptions.ColisaoEntreSondasException;
import br.com.elo7.domain.exceptions.SondaEstourouABordaException;
import br.com.elo7.domain.exceptions.SondaNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(SondaNaoEncontradaException.class)
	public ResponseEntity<?> handleExceptionEntity(SondaNaoEncontradaException e, WebRequest request){
		
		var problem = createProblemBuilder(HttpStatus.NOT_FOUND, ProblemType.RECURSO_NAO_ENCONTRADO, e.getMessage()).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(SondaEstourouABordaException.class)
	public ResponseEntity<?> handleExceptionEntity(SondaEstourouABordaException e, WebRequest request){
		
		var problem = createProblemBuilder(HttpStatus.CONFLICT, ProblemType.ERRO_NEGOCIO, e.getMessage()).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(ColisaoEntreSondasException.class)
	public ResponseEntity<?> handleExceptionEntity(ColisaoEntreSondasException e, WebRequest request){
		
		var problem = createProblemBuilder(HttpStatus.CONFLICT, ProblemType.ERRO_NEGOCIO, e.getMessage()).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType type, String detail){
		return Problem.builder()
				.status(status.value())
				.type(type.getUri())
				.title(type.getTitle())
				.timestamp(OffsetDateTime.now())
				.detail(detail);				
	}
}
