package com.springtraining.geocoderclient.services;

import com.springtraining.geocoderclient.entities.Site;
import com.springtraining.geocoderclient.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GeocoderService {
    private static final String BASE = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

    private RestTemplate restTemplate;

    @Autowired
    public GeocoderService (RestTemplateBuilder builder){
        restTemplate = builder.build();
    }

    public Site getLatLng(String... address){
        String joinedAddress = String.join(",", address);
        String encodedAddress = "";

        encodedAddress = URLEncoder.encode(joinedAddress, StandardCharsets.UTF_8);
        Response response = restTemplate.getForObject(
                String.format("%s?address=%s&key=%s", BASE, encodedAddress, KEY), Response.class );

        Site site = null;
        if (response != null){
            site = new Site(response.getFormattedAddress(),
                    response.getLocation().getLat(),
                    response.getLocation().getLng());
        }
        return site;
    }
}
