package com.oday.soapExampleProducer.producingwebservice;


import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.oday.movies.MovieRepository;

import Configration.AerospikeConfiguration;

@SpringBootApplication


public class ProducingWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducingWebServiceApplication.class, args);
	}

	
}
