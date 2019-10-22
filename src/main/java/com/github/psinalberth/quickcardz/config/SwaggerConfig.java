package com.github.psinalberth.quickcardz.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.github.psinalberth"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo
			("Quickcardz API",
				"API created using Spring v2.1.9 for flashcards creation.", 
				"1.0", 
				"x", 
				new Contact("Inalberth Pinheiro Santos", "http://psinalberth.dev", "contato@psinalberth.dev"), 
				"License of API",
				"http://www.apache.org/licenses/LICENSE-2.0", 
			Collections.emptyList());
	}
}