package com.sparta.daniel.injector;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.daniel.dto.DTOPeople;
import com.sparta.daniel.dto.ParentDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Injector {

    public static DTOPeople injectDTOPeople(String url) {
        DTOPeople dtoPeople = new DTOPeople();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
//            dtoPeople = objectMapper.readValue(new URL(url), DTOPeople.class);
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            dtoPeople = objectMapper.readValue(httpResponse.body(), DTOPeople.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("IOException or InterruptedException found during injection");
        }
        return dtoPeople;
    }


    public static ParentDTO injectDTOGeneric(String url) {
        if (url.contains("https")) {

        } else if (url.contains("http")){
            String tempFirst = url.substring(0,4);
            String tempLast = url.substring(4);

            url = tempFirst + "s" + tempLast;
        } else {
            System.err.println("No valid url for Inject Generic");
        }

        ParentDTO dtoChild = Factory.dtoFactory(Factory.dtoNumberCheck(url));
        ObjectMapper objectMapper = new ObjectMapper();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            dtoChild = objectMapper.readValue(httpResponse.body(), dtoChild.getClass());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("IOException or InterruptedException found during generic injection");
        }
        return dtoChild;

    }
}
