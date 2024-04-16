package com.ssafy.ws;

public class Reader implements Person{

	@Override
	public void doSomething() throws RestroomException {
		
		System.out.println("열심히 책을 읽습니다.");
		
		if(Math.random()>0.5) {
			throw new RestroomException();
		}
		
	}

}
