package br.com.elo7.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Autowired
	private Properties properties;
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.elo7.api." + properties.getVersion() + ".resource"))
					.paths(PathSelectors.ant("/" + properties.getVersion() + "/**"))
					.build()
					.apiInfo(apiInfo());
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Elo7 API")
				.description("Teste de Programação Elo7")
				.version(properties.getVersion())
				.contact(new Contact("Wanderson Santiago", "https://github.com/wanderson-santiago", "wandersonsantiago86@gmail.com"))
				.build();
	}
}
