package com.pfcti.spring.developer.training.pfcti;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class SpringDeveloperTrainingPfctiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDeveloperTrainingPfctiApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}

}
