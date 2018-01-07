package com.hcl.satish.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



// This class is used for connecting the database
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(entityManagerFactoryRef = "barEntityManagerFactory", 
transactionManagerRef = "barTransactionManager",
basePackages = {
		"com.hcl.satish.demo.repository" })
public class DataBaseConfig {

	@Bean(name = "barDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "barEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("barDataSource") DataSource dataSource) {

		return builder.dataSource(dataSource).packages("com.hcl.satish.demo.entity")
				.persistenceUnit("demo").build();
	}

	@Bean(name = "barTransactionManager")
	@ConditionalOnMissingBean(PlatformTransactionManager.class)
	public PlatformTransactionManager barTransactionManager(
			@Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
		return new JpaTransactionManager(barEntityManagerFactory);
	}

}