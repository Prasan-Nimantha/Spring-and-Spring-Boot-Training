package com.training.demo.controllers;

import com.training.demo.entities.Greeting;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerTests {

    @Test
    public void greetWithoutName(@Autowired TestRestTemplate template){
        ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType());
        Greeting response = entity.getBody();
        if(response != null){
            assertEquals("Hello, World!", response.getMessage());
        }
    }

    @Test
    public void greetWithName(@Autowired TestRestTemplate template){
        Greeting response = template.getForObject("/rest?name=Prashan", Greeting.class);
        assertEquals("Hello, Prashan!", response.getMessage());
    }
}
