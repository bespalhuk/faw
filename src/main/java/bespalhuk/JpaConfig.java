package bespalhuk;

import bespalhuk.infra.persistence.DatabaseProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

	@Autowired
	private DatabaseProperties databaseProperties;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPersistenceUnitName("unit");
		return factoryBean;
	}

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(databaseProperties.getDriver());
		dataSource.setUrl(databaseProperties.getUrl());
		dataSource.setUsername(databaseProperties.getUsername());
		dataSource.setPassword(databaseProperties.getPassword());
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(5);
		dataSource.setMaxIdle(2);
		dataSource.setMinIdle(1);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setValidationQuery("SELECT 1 FROM DUAL");
		dataSource.setTimeBetweenEvictionRunsMillis(1_800_000);
		dataSource.setNumTestsPerEvictionRun(3);
		dataSource.setMinEvictableIdleTimeMillis(1_800_000);
		return dataSource;
	}

}
