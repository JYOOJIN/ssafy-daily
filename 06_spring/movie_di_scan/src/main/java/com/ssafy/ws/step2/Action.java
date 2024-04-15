package com.ssafy.ws.step2;

import org.springframework.stereotype.Component;

//component-scan 대상이 되는 클래스 어노테이션
@Component
public class Action implements Movie{
	
	
	@Override
	public String getInfo() {
		 return "액션";
	}
	

	
}
