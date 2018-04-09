package com.demo.configuration;

import org.springframework.beans.factory.annotation.Value;

public class DevelopmentEnv implements Envconfig {

	@Value("${spring.datasource.url.dev}")
	private String mySqlDBUrl;

	@Value("${spring.datasource.user.dev}")
	private String mySqlDBUser;

	@Value("${spring.datasource.password.dev}")
	private String mySqlDBPassword;

	@Value("${spring.datasource.db.driver}")
	private String mySqlDBDriver;


	@Value("${scaffold.auth.redis.db.max.conn.dev}")
	private int redisMaxConn;

	@Value("${scaffold.auth.redis.db.min.idle.conn.dev}")
	private int redisMinIdleConn;

	@Value("${scaffold.auth.redis.db.max.idle.conn.dev}")
	private int redisMaxIdleConn;

	@Value("${scaffold.auth.redis.db.ip.dev}")
	private String redisIp;

	@Value("${scaffold.auth.redis.db.port.dev}")
	private int redisPort;

	@Value("${scaffold.server.app.url.dev}")
	private String appUrl;

	@Override
	public String getEnvironment() {
		return "Dev Environment";
	}

	@Override
	public String getMySqlDBUrl() {
		return mySqlDBUrl;
	}

	@Override
	public String getMySqlDBUser() {
		return mySqlDBUser;
	}

	@Override
	public String getMySqlDBPassword() {
		return mySqlDBPassword;
	}

	@Override
	public String getMySqlDBDriver() {
		return mySqlDBDriver;
	}




	@Override
	public int getRedisMaxConn() {
		return redisMaxConn;
	}

	@Override
	public int getRedisMinIdleConn() {
		return redisMinIdleConn;
	}

	@Override
	public int getRedisMaxIdleConn() {
		return redisMaxIdleConn;
	}

	@Override
	public String getRedisIp() {
		return redisIp;
	}

	@Override
	public int getRedisPort() {
		return redisPort;
	}


	@Override
	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
}
