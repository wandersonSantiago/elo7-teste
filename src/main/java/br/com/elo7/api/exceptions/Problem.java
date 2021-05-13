package br.com.elo7.api.exceptions;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Problem {
	
	private Integer status;
	private String type;
	private String title;
	private String detail;
	private OffsetDateTime timestamp;	

}
