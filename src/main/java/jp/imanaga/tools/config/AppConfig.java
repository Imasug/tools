package jp.imanaga.tools.config;

import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public ResourceBundle resourceBundle() {
		return ResourceBundle.getBundle("messages");
	}

}
