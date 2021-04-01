package com.springtraining.restclient.services;

import com.springtraining.restclient.json.JokeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service   //another annotation spring provides based on component
public class JokeServices {

    private static final String BASE = "https://api.icndb.com/jokes/random?limitTo=[nerdy]";

    private RestTemplate restTemplate;

    @Autowired  //If you have a single constructor in a spring class, dependencies are autowired automatically.
    public JokeServices(RestTemplateBuilder builder){
        restTemplate = builder.build();
    }

    public String getJoke(String first, String last){
        String url = String.format("%s&firstName=%s&lastName=%s", BASE, first, last);
        JokeResponse response = restTemplate.getForObject(url, JokeResponse.class);
        String output = "";
        if (response != null ){
            output = response.getValue().getJoke();
        }
        return output;
    }
}
