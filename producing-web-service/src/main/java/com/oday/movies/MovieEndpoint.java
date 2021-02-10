package com.oday.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import Configration.AerospikeConfiguration;
import io.pheely.get_movie_web_service.GetMovieRequest;
import io.pheely.get_movie_web_service.GetMovieResponse;
import io.pheely.get_movie_web_service.Movie;

@Endpoint
@ComponentScan(basePackageClasses = AerospikeConfiguration.class)
public class MovieEndpoint {

	private static final String NAMESPACE_URI = "http://pheely.io/get-movie-web-service";
	
	public MovieRepository movieRepository;

	
	
	@Autowired
	public MovieEndpoint(MovieRepository movieRepository) {
		System.out.println("oday doqsahdonocfjdsa\n cjxdoacnjdnsajvcdsbhvic\nidjsacndsajnv jdsijzbvc jdsz");

		this.movieRepository = movieRepository;
	}




	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieRequest")
	@ResponsePayload
	public GetMovieResponse getMovie(@RequestPayload GetMovieRequest request) {
		GetMovieResponse response = new GetMovieResponse();
		Movie m=movieRepository.findById(request.getName()).get();
		response.setMovie(m);

		return response;
	}
}