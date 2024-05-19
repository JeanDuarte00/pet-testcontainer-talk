package com.fractal.demotestcontainer.config;


import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Configuration
@Testcontainers
public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {

	public static final String IMAGE_VERSION = "postgres:15-alpine";
	public static final String DATABASE_NAME = "petDB";

	@Container
	public static PostgreSQLContainer<PostgresContainer> container =  new PostgresContainer().withDatabaseName(DATABASE_NAME);

	public PostgresContainer() {
		super(IMAGE_VERSION);
	}

	@Override
	public void start() {
		super.start();
		System.setProperty("DATABASE_URL", container.getJdbcUrl());
		System.setProperty("DATABASE_USERNAME", container.getUsername());
		System.setProperty("DATABASE_PASSWORD", container.getPassword());
	}

	@Override
	public void stop(){
		super.stop();
	}

}