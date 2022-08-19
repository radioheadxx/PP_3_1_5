package org.example;

import org.example.config.MyConfig;
import org.example.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        Communication communication = context.getBean("communication", Communication.class);

        String sessionID = communication.getAllUsers(headers);
        headers.set("Cookie", sessionID);

//      Сохранить пользователя с id = 3, name = James, lastName = Brown, age = на ваш выбор. В случае успеха вы получите первую часть кода.
        User user = new User(3L, "James", "Brown", (byte) 27);
        communication.saveUser(headers, user);

//      Изменить пользователя с id = 3. Необходимо поменять name на Thomas, а lastName на Shelby. В случае успеха вы получите еще одну часть кода.
        User user2 = new User(3L, "Thomas", "Shelby", (byte) 27);
        communication.updateUser(headers, user2);

//      Удалить пользователя с id = 3. В случае успеха вы получите последнюю часть кода.
        communication.deleteUser(headers, 3);
    }
}

