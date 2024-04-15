package com.ssafy.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MovieTest {
    
    // Spring DI
    public static void main(String[] args) {
        // xml bean config
        // classpath 기준으로 상대 경로
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    
        // bean id, Type 
        Audience audience=context.getBean("audience",Audience.class);
        audience.watch();
        
        Comic comic=(Comic)context.getBean("comic");
        Comic comic2=(Comic)context.getBean("comic",Comic.class); //이름과 클래스 모두 설정
        //동일한 class에 대해서 bean이 다중 설정 되어 있는 경우에는 오류 발생
        //즉, 하나의 class 에 대해서 하나의 bean 설정 시에 가능
        //Comic comic3=(Comic)context.getBean(Comic.class);
        
        System.out.println(comic);
        System.out.println(comic2);
        //System.out.println(comic3);
    }
    
    
    // 기존 방식대로 객체 생성 사용
    public static void main1(String[] args) {
        Movie m = new Action();
        Audience au = new Audience();
        au.setMovie(m);
        au.watch();
        
    }
}
