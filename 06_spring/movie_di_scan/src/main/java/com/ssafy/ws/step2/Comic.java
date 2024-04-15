package com.ssafy.ws.step2;

import org.springframework.stereotype.Component;

//@Component
public class Comic implements Movie{
	
	
	public Comic() {
		System.out.println("call Comic()");
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "코믹";
	}


}
