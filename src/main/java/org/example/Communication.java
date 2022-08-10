package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public String getAllUsers(HttpHeaders headers) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        return responseEntity.getHeaders().get("Set-Cookie").get(0);
    }

    public void saveUser(HttpHeaders headers, User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void updateUser(HttpHeaders headers, User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void deleteUser(HttpHeaders headers, int id) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println(responseEntity.getBody());
    }

}
