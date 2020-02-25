package com.fradantim.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fradantim.movieApp.models.Rating;
import com.fradantim.movieApp.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId){
		return new Rating(movieId, 2);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId){
		return new UserRating(
				Arrays.asList(
					new Rating("1111", 2),
					new Rating("1112", 3),
					new Rating("1114", 1)
					)
				);
	}
}
