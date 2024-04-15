package com.ssafy.ws;

public class Action implements Movie{
	
	public Action() {
		System.out.println("call Action()");
	}

	public Action(String data1) {
		System.out.println("call Action() : "+data1);
	}
	
	public Action(String data1,String data2,String data3) {
		System.out.println(data1+" , "+data2+" , "+data3);
	}
	
	@Override
	public String getInfo() {
		 return "액션";
	}
	

	
}
