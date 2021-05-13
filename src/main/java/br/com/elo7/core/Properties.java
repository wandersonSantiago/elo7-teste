package br.com.elo7.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("elo7")
public class Properties {
	

	private String basePackage;
	private String version;
	
	
}
