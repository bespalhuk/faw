package bespalhuk;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.env.MockEnvironment;

public class MockedEnvironment implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
		MockEnvironment environment = new MockEnvironment();
		environment.setProperty("DATABASE_URL", "mysql://faw:faw@localhost:3306/faw");
		configurableApplicationContext.setEnvironment(environment);
	}

}
