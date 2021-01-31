package cl.gpsCar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GpsCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(GpsCarApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//registry.addMapping("/topic").allowedOrigins("http://localhost:8100");
				
		        registry.addMapping("/**")
		        .allowedOrigins("http://localhost:8100")
		        .allowedMethods("GET", "POST")
		        .allowedHeaders("Origin", "Accept", "Content-Type", "Authorization")
		        .allowCredentials(false)
		        .maxAge(3600);
			}
		};
	}
}
