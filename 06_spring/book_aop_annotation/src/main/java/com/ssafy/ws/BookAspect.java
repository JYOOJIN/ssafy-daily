package com.ssafy.ws;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BookAspect {
	
	@Before("execution(* com.ssafy.ws.Person.doSomething())")
	public void before() {
		System.out.println("도서관에 갑니다.");
	}
	
	@AfterReturning("execution(* com.ssafy.ws.Person.doSomething())")
	public void afterReturn() {
		System.out.println("읽은 것을 정리합니다.");
	}
	
	@AfterThrowing("execution(* com.ssafy.ws.Person.doSomething())")
	public void afterThrow(){
		System.out.println("화장실에 갑니다.");
	}
	
	@After("execution(* com.ssafy.ws.Person.doSomething())")
	public void after() {
		System.out.println("집으로 돌아갑니다.");
	}
}
