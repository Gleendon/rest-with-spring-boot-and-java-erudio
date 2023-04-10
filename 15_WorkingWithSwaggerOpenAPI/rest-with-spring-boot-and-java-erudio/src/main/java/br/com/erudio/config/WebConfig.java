package br.com.erudio.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.erudio.serialization.converter.YamlJackson2HttpMesageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");
	
	//Cors: Obtendo quais origins serão permitidas a partir das configurações do application.yml 
	@Value("${cors.originPatterns:default}")
	private String corsOriginPatterns = "";
	
	//Cors: definindo quais operações as origins terão acesso
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		var allowedOrigins = corsOriginPatterns.split(",");
		registry.addMapping("/**")
			//.allowedMethods("GET", "PUT", "POST")
			.allowedMethods("*")
			.allowedOrigins(allowedOrigins)
			.allowCredentials(true);
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMesageConverter());
	}
	

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		//Via QUERY PARAM http://localhost:8080/api/person/v1?mediaType=xml
//		configurer.favorParameter(true)
//			.parameterName("mediaType")
//			.ignoreAcceptHeader(true)
//			.useRegisteredExtensionsOnly(false)
//			.defaultContentType(MediaType.APPLICATION_JSON)
//				.mediaType("json", MediaType.APPLICATION_JSON)
//				.mediaType("xml", MediaType.APPLICATION_XML)
//				.mediaType("yml", MEDIA_TYPE_APPLICATION_YML);
		
		//Via HEADER PARAM http://localhost:8080/api/person/v1
		configurer.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
	}
}
