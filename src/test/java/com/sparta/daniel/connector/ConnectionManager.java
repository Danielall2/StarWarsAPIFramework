package com.sparta.daniel.connector;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionManager {

    // Inspiration from Manish's ConnectionManager

    private static final String BASEURL = "https://swapi.dev/api/";
    private String URL;

    // Want a method which provides a httpResponse so I don't call it every time in test

    public static HttpResponse<String> getConnectionResponse(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = null;

        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error in getting Status Code");
        }

        return httpResponse;
    }

    public static String getConnection(String endURL) {
        // There needs to be a way check that endURL has been entered correctly
        // Needs to be of form word/number/
        return BASEURL + endURL;
    }

    public static int getStatusCode(String endURL) {
        HttpResponse<String> httpResponse = getConnectionResponse(getConnection(endURL));
        return httpResponse.statusCode();
    }

    public static HttpHeaders getHeaders(String endURL) {
        HttpResponse<String> httpResponse = getConnectionResponse(getConnection(endURL));
        return httpResponse.headers();
    }


}
