package com.oday.movies;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import Configration.AerospikeConfiguration;
import io.pheely.get_movie_web_service.Movie;
@Component
@ComponentScan(basePackageClasses = AerospikeConfiguration.class)
public class initMovies implements CommandLineRunner {
	
	public MovieRepository movieRepository;
	
	@Autowired
	public initMovies(MovieRepository movieRepository) {
		
		this.movieRepository = movieRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		movieRepository.deleteAll();
		Movie titanic = new Movie();
		titanic.setName("Titanic");
		titanic.setYear(1997);
		titanic.setCountry("USA");
		titanic.setGenra("epic romance-disaster");
		titanic.setDirector("James Cameron");

		movieRepository.save(titanic);

		Movie pearlHarbor = new Movie();
		pearlHarbor.setName("Pearl Harbor");
		pearlHarbor.setYear(2001);
		pearlHarbor.setCountry("USA");
		pearlHarbor.setGenra("romantic period war drama");
		pearlHarbor.setDirector("Michael Bay");

		movieRepository.save(pearlHarbor);

		Movie spectre = new Movie();
		spectre.setName("Spectre");
		spectre.setYear(2015);
		spectre.setCountry("USA");
		spectre.setGenra("spy");
		spectre.setDirector("Sam Mendes");

		movieRepository.save(spectre);
	}

	
	
	
}
