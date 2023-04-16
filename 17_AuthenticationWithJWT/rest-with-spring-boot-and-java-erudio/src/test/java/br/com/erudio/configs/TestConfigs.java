package br.com.erudio.configs;

//configuração que o rest assured irá utilizar para conectar com os testes da api
public class TestConfigs {
	public static final int SERVER_PORT = 8888;
	
	public static final String HEADER_PARAM_AUTHORIZATION = "Authorization";
	public static final String HEADER_PARAM_ORIGIN = "Origin";

	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_XML = "application/xml";
	public static final String CONTENT_TYPE_YML = "application/x-yaml";
	
	public static final String ORIGIN_ERUDIO = "https://erudio.com.br";
	public static final String ORIGIN_SEMERU = "https://semeru.com.br";
}
