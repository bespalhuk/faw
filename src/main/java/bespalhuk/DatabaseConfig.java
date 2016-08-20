package bespalhuk;

import bespalhuk.infra.persistence.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DatabaseConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DatabaseProperties databaseConfiguration() {
		return DatabaseProperties
				.of(environment.getProperty("DATABASE_URL", "mysql://faw:faw@localhost:3306/faw"));
	}

}
