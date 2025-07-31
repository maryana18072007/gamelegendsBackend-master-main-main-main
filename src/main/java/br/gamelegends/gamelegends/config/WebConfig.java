package br.gamelegends.gamelegends.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

   /*
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Habilita CORS para todas as rotas
                .allowedOrigins("http://localhost:5173") // Permite requisições de todas as rotas do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite esses métodos
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite envio de cookies ou credenciais
    }
    */
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
				.allowCredentials(false).maxAge(3600);
	}
}