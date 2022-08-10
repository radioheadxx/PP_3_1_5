package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";


    public String getAllUsers(HttpEntity<User> entity) {
//        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    public void saveUser(User user) {
        long id = user.getId();
        if(id==0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, user, String.class);
        } else {
            restTemplate.put(URL, user);
        }
    }

    public void deleteUser(int id) {
        restTemplate.delete(URL + "/" + id);
    }

}
