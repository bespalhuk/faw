package bespalhuk;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

@ContextConfiguration(classes = {
		DatabaseConfig.class,
		JpaConfig.class,
		RepositoryConfig.class,
		TestConfig.class,
}, initializers = MockedEnvironment.class)
public abstract class AbstractIT extends AbstractTransactionalTestNGSpringContextTests {
}
