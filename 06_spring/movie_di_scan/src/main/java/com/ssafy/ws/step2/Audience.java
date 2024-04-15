package com.ssafy.ws.step2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Audience {

	private Movie movie;
	
	@Autowired 
	public void setMovie(Movie movie) {
		this.movie=movie;
	}
	
	public void watch() {
		System.out.println(movie.getInfo()+" 영화를 관람합니다.");
	}
}
