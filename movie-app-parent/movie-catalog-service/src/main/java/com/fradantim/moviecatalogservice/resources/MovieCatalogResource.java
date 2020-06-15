package com.fradantim.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fradantim.movieApp.models.CatalogItem;
import com.fradantim.movieApp.models.Movie;
import com.fradantim.movieApp.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class);
		
		
		return ratings.getUserRating().stream().map(rating -> {
			//example with RestTemplate
			//this is not an actual url, RestTemplate will ask the Eureka server what the real url is
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(),Movie.class);
			
			//example with WebClient
			/*Movie movie = webClientBuilder.build()
				.get()
				.uri("http://localhost:8082/movies/"+rating.getMovieId())
				.retrieve()
				.bodyToMono(Movie.class) //mono > a promise of what the future will bring
				.block();	//block the execution until the new async thread returns an object
							//under reactive programming the thread should not be blocked
			*/
			return new CatalogItem(movie.getName(),"hardcoded description", rating.getRating());
		})
		.collect(Collectors.toList()); 
		
		
		
		//for each movie ID, call movie info service and get details
		
		//put them all tohether
	}
}
