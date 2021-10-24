package ro.kronsoft.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ro.kronsoft.training.config.security.CustomCorsConfigurer;

@Configuration
public class Beans {
	@Bean
	public WebMvcConfigurer getCorsConfigurer() {
		return new CustomCorsConfigurer();
	}
}
