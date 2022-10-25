package com.multidb.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = "com.multidb.employeedao", 
						entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class EmployeeConfig {
	@Primary
	@Bean(name = "employeeDataSourceProperties")
	@ConfigurationProperties("spring.datasource.employee")
	public DataSourceProperties channelDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean(name = "employeeDataSource")
	@ConfigurationProperties("spring.datasource.employee.configuration")
	public DataSource channelDataSource(
			@Qualifier("employeeDataSourceProperties") DataSourceProperties channelDataSourceProperties) {
		
		return channelDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}


	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier("employeeDataSource") DataSource dataSource) {
		Map<String, String> employeeProp = new HashMap<>();
		employeeProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		employeeProp.put("hibernate.hbm2ddl.auto", "update");
		EntityManagerFactoryBuilder builder= new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(),employeeProp, null);
		return builder.dataSource(dataSource)
				.packages("com.multidb.model.employee")
				.persistenceUnit("employee").build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
