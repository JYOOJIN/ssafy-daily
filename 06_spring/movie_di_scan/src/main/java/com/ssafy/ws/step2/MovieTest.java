package com.ssafy.ws.step2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MovieTest {
    
    // Spring DI
    public static void main(String[] args) {
    	
        // classpath 기준으로 상대 경로
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
    
        Audience audience=context.getBean("audience",Audience.class);
        audience.watch();
        
        Action action=context.getBean("action",Action.class);
        Comic comic=context.getBean("comic",Comic.class);
        
        
        
       
    }
    
    

}
