package com.reactive.reactiveprogramming.services;

import java.util.Arrays;
import java.util.List;

import com.reactive.reactiveprogramming.domain.Review;

import reactor.core.publisher.Flux;

public class ReviewService {
	
	public Flux<Review> getReviews(long bookId){
		
		List<Review> list=Arrays.asList(new Review(10,bookId,2,"Good Book"),new Review(6,bookId,2,"Worth reading")
				,new Review(4,bookId,2,"Worth reading"));
		
		return Flux.fromIterable(list);
		
	}

}
