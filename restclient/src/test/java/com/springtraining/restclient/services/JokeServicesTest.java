package com.springtraining.restclient.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class JokeServicesTest {

    private Logger logger = LoggerFactory.getLogger(JokeServicesTest.class);

    @Autowired
    private JokeServices service;

    @Test
    void getJoke() {
        String joke = service.getJoke("Dinuk", "Liyanarachchi");
        logger.info(joke);
        assertTrue(joke.contains("Dinuk") || joke.contains("Liyanarachchi"));
    }
}