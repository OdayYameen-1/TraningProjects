package com.oday.movies;


import io.pheely.get_movie_web_service.Movie;

import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;




public interface MovieRepository extends AerospikeRepository<Movie,String>{

	
	
		

}