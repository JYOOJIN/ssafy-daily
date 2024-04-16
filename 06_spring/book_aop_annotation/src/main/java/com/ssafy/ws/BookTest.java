package com.ssafy.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BookTest {
	
	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		Person reader=context.getBean("reader",Person.class);
		
		try {
			reader.doSomething();
		} catch (RestroomException e) {
			
		}
	}
}
