package com.demo.configuration;

public interface Envconfig {
	String getEnvironment();

	// for mysql
	String getMySqlDBUrl();

	String getMySqlDBUser();

	String getMySqlDBPassword();

	String getMySqlDBDriver();

	String getAppUrl();

	int getRedisMaxConn();

	int getRedisMinIdleConn();

	int getRedisMaxIdleConn();

	String getRedisIp();

	int getRedisPort();

}
