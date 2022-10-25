package com.multidb.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@EnableJpaRepositories(basePackages = "com.multidb.addressdao", entityManagerFactoryRef = "addressEntityManagerFactory" ,
transactionManagerRef = "addressTransactionManager"
)
public class AddressConfig {
	
	@Bean(name = "addressDataSourceProperties")
	@ConfigurationProperties("spring.datasource.address")
	public DataSourceProperties channelDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "addressDataSource")
	@ConfigurationProperties("spring.datasource.address.configuration")
	public DataSource channelDataSource(
			@Qualifier("addressDataSourceProperties") DataSourceProperties channelDataSourceProperties) {
		
		return channelDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "addressEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier("addressDataSource") DataSource dataSource) {
		Map<String, String> addressProp = new HashMap<>();
		addressProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		addressProp.put("hibernate.hbm2ddl.auto", "update");
		EntityManagerFactoryBuilder builder= new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(),addressProp, null);
		return builder.dataSource(dataSource)
				.packages("com.multidb.model.address")
				.persistenceUnit("address")
				.build();
	}


	@Bean(name = "addressTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("addressEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
	