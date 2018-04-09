package com.demo.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MysqlDataSource {
    @Autowired
    private Envconfig envConfig;

    @Bean
    public DataSource dataSource() {

        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(envConfig.getMySqlDBUrl());
        poolProperties.setDriverClassName(envConfig.getMySqlDBDriver());
        poolProperties.setUsername(envConfig.getMySqlDBUser());
        poolProperties.setPassword(envConfig.getMySqlDBPassword().trim());
        poolProperties.setJmxEnabled(true);
        poolProperties.setTestWhileIdle(true);
        poolProperties.setTestOnBorrow(true);

        DataSource datasource = new DataSource();
        datasource.setPoolProperties(poolProperties);

        return datasource;
    }














	/*@Autowired
	private MultiTenantProps multitenancyProperties;

	@Bean(name = {"dataSource","dataSource1"})
	@ConfigurationProperties(prefix = "employee.multi-tenant.datasource.datasource1")
	public DataSource dataSource1() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource1().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource1().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource1().getUsername())
				.password(this.multitenancyProperties.getDatasource1().getPassword())
				.url(this.multitenancyProperties.getDatasource1().getUrl());
		System.out.println("************************"+factory.build());
		return (DataSource)factory.build();
	}

	@Bean(name = "dataSource2")
	@ConfigurationProperties(prefix = "employee.multi-tenant.datasource.datasource2")
	public DataSource dataSource2() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource2().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource2().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource2().getUsername())
				.password(this.multitenancyProperties.getDatasource2().getPassword())
				.url(this.multitenancyProperties.getDatasource2().getUrl());
		System.out.println("************************"+factory.build());
		return (DataSource) factory.build();
	}*/
/*
	@Bean(name = "dataSource3")
	@ConfigurationProperties(prefix = "employee.multi-tenant.datasource.datasource3")
	public DataSource dataSource3() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource3().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource3().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource3().getUsername())
				.password(this.multitenancyProperties.getDatasource3().getPassword())
				.url(this.multitenancyProperties.getDatasource3().getUrl());
		System.out.println("************************"+factory.build());
		return (DataSource) factory.build();
	}*/


}