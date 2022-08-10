package org.example;

import org.example.config.MyConfig;
import org.example.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> entity = new HttpEntity<>(headers);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Communication communication = context.getBean("communication", Communication.class);

        String allUsers = communication.getAllUsers(entity);
        System.out.println(allUsers);
    }
}

