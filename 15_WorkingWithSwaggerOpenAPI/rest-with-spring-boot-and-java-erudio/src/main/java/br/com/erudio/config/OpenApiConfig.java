package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	//descrição da API para a documentação a ser gerada pelo Swagger 
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API with JAVA 18 and Spring Boot 3")
						.version("v1")
						.description("Description API")
						.termsOfService("http://")
						.license(new License()
								.name("Apache 2.0")
								.url("http://")
								)
						);
	}
	

}
