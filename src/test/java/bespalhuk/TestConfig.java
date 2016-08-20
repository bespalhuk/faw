package bespalhuk;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackageClasses = Application.class,
		includeFilters = @ComponentScan.Filter(Component.class),
		excludeFilters = @ComponentScan.Filter(Configuration.class))
public class TestConfig {
}
