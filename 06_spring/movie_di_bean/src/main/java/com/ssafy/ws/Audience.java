package com.ssafy.ws;

public class Audience {

	private Movie movie;
	
	public void setMovie(Movie movie) {
		this.movie=movie;
	}
	
	public void watch() {
		System.out.println(movie.getInfo()+" 영화를 관람합니다.");
	}
}
