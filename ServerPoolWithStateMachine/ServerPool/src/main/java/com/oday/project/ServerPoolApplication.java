package com.oday.project;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;


@SpringBootApplication
@EnableStateMachine
public class ServerPoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerPoolApplication.class, args);
	}
	
	  
}
